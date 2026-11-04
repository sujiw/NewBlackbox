package top.niunaijun.blackboxa.api

/**
 * 内置 API 文档页：`GET /v1/docs`（也接受 `/`、`/docs`，不校验 token）。
 *
 * 纯手写 HTML，无外部依赖，离线可用。端点 / 参数 / 返回结构全部对应
 * `ApiServer` + `ConfigOps` + `TaskEngine` 的实际实现。
 */
object ApiDocs {

    private data class Param(
        val name: String,
        val type: String,
        val required: String,
        val desc: String
    )

    private fun params(vararg rows: Param): String {
        val body = rows.joinToString("\n") { p ->
            "<tr><td><code>${p.name}</code></td><td>${p.type}</td>" +
                    "<td class=\"req\">${p.required}</td><td>${p.desc}</td></tr>"
        }
        return """
            <table class="params">
            <tr><th>字段</th><th>类型</th><th>必填</th><th>说明</th></tr>
            $body
            </table>
        """.trimIndent()
    }

    private fun section(id: String, title: String, body: String): String = """
        <section id="$id">
        <h2>$title</h2>
        $body
        </section>
    """.trimIndent()

    private fun endpoint(method: String, path: String, desc: String, body: String): String = """
        <h3><span class="badge m-${method.lowercase()}">$method</span> <code>$path</code></h3>
        <p class="desc">$desc</p>
        $body
    """.trimIndent()

    private fun pre(label: String, content: String): String =
        "<div class=\"block\"><div class=\"label\">$label</div><pre>${escape(content)}</pre></div>"

    private fun escape(text: String): String =
        text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")

    // ------------------------------------------------------------------ 内容

    private val NAV = listOf(
        "overview" to "概览",
        "auth" to "认证与错误",
        "health" to "健康检查",
        "apps" to "应用管理",
        "config" to "应用配置",
        "tasks" to "任务",
        "taskmode" to "任务模式",
        "models" to "数据模型",
        "steps" to "任务步骤",
        "examples" to "完整示例",
        "openapi" to "OpenAPI"
    )

    private fun overview(base: String) = section(
        "overview", "概览", """
        <p>BlackBox 内置的本地 REST 服务，用于在虚拟空间里安装 / 启动应用，并给每个应用注入
        代理、Google 广告 ID 与整份设备档案。所有接口返回 JSON（文档页除外），UTF-8 编码。</p>
        ${params(
            Param("Base URL", "string", "-", "<code>$base</code>"),
            Param("API 版本", "int", "-", "当前为 <code>${ApiServer.VERSION}</code>，路径前缀 <code>/v1</code>"),
            Param("端口", "int", "-", "默认 8080，可在「自动化」页修改后重启服务"),
            Param("并发", "int", "-", "单用户容器，任务串行执行（一次只跑一个 job）"),
            Param("持久化", "file", "-", "任务队列、每应用配置写在宿主私有目录，进程重启后恢复")
        )}
        <p>最小请求：</p>
        ${pre("curl", "curl -s -H \"X-Api-Token: <token>\" $base/v1/ping")}
    """.trimIndent()
    )

    private fun auth(token: String) = section(
        "auth", "认证与错误", """
        <p>除文档页外的所有请求都必须带请求头 <code>X-Api-Token</code>，值为「自动化」页里显示的 token
        （可在该页轮换）：</p>
        ${pre("请求头", "X-Api-Token: $token")}
        <p>缺失或错误时返回 <code>401</code>：</p>
        ${pre("401 响应", "{\"success\":false,\"error\":\"invalid token\"}")}
        <p>错误响应统一为 <code>{"success": false, "error": "..."}</code>，HTTP 状态码：</p>
        ${params(
            Param("200", "-", "-", "成功"),
            Param("400", "-", "-", "参数缺失 / 类型不对（如 install 没给 url/path/package）"),
            Param("401", "-", "-", "token 不匹配"),
            Param("404", "-", "-", "路径不存在，或任务 id 不存在"),
            Param("405", "-", "-", "路径存在但 HTTP 方法不对"),
            Param("500", "-", "-", "容器内部异常（安装失败、启动异常等）")
        )}
    """.trimIndent()
    )

    private fun health(base: String) = section(
        "health", "健康检查", """
        ${endpoint("GET", "/v1/ping", "探测服务是否存活，并返回端口与文档地址。", pre(
            "响应",
            """
            {
              "ok": true,
              "version": 1,
              "port": 8080,
              "docs": "/v1/docs"
            }
            """.trimIndent()
        ))}
        <p>文档页 <code>/v1/docs</code>、<code>/docs</code>、<code>/</code> 不需要 token，可直接用浏览器打开：
        <code>$base/v1/docs</code></p>
        <p>需要导入 Postman / Apifox / Swagger UI 时用 OpenAPI 描述：
        <code>$base/v1/openapi.json</code></p>
    """.trimIndent()
    )

    private fun apps(base: String) = section(
        "apps", "应用管理", """
        ${endpoint("GET", "/v1/apps", "列出容器内已安装的虚拟应用（只返回包名）。", pre(
            "响应",
            """
            { "apps": [ { "package": "com.example.app" }, { "package": "com.example.two" } ] }
            """.trimIndent()
        ))}

        ${endpoint(
            "POST", "/v1/apps/install",
            "安装 APK：三者任选其一（按 url → path → package 的优先级取第一个非空值）。安装成功后可选地立即写入配置。",
            params(
                Param("url", "string", "三选一", "远程地址，容器内下载后安装"),
                Param("path", "string", "三选一", "容器可见的本地路径，如 <code>/sdcard/a.apk</code>"),
                Param("package", "string", "三选一", "设备上已安装的同包名应用，直接复制进容器"),
                Param("config", "object", "否", "安装成功后写入的配置，结构同 <code>/v1/apps/{pkg}/config</code>")
            ) + pre(
                "请求",
                """
                {
                  "url": "https://example.com/app.apk",
                  "config": {
                    "proxy": { "enabled": true, "type": "socks5", "host": "1.2.3.4", "port": 1080 },
                    "device": { "model": "SM-S938B", "brand": "samsung" }
                  }
                }
                """.trimIndent()
            ) + pre(
                "响应",
                """
                {
                  "success": true,
                  "package": "com.example.app",
                  "message": "",
                  "config": { "package": "com.example.app", "proxy": {...}, "gaid": null, "device": {...} }
                }
                """.trimIndent()
            )
        )}

        ${endpoint(
            "POST", "/v1/apps/launch", "启动已安装的虚拟应用。",
            params(Param("package", "string", "是", "目标包名")) +
                    pre("请求", "{\"package\":\"com.example.app\"}") +
                    pre("响应", "{\"success\":true,\"package\":\"com.example.app\"}")
        )}

        ${endpoint(
            "POST", "/v1/apps/stop", "停止指定应用；省略 package 时停止所有正在运行的虚拟应用。",
            params(Param("package", "string", "否", "不传 = 全部运行中的应用")) +
                    pre("请求", "{\"package\":\"com.example.app\"}") +
                    pre("响应", "{\"success\":true,\"stopped\":[\"com.example.app\"]}")
        )}

        ${endpoint(
            "POST", "/v1/apps/uninstall",
            "先停止再卸载，并清除该应用保存的 proxy / gaid / device 配置。",
            params(Param("package", "string", "是", "目标包名")) +
                    pre("响应", "{\"success\":true,\"package\":\"com.example.app\"}")
        )}
    """.trimIndent()
    )

    private fun config(base: String) = section(
        "config", "应用配置", """
        <p>每个应用最多三块配置：<code>proxy</code>（网络代理）、<code>gaid</code>（广告 ID）、
        <code>device</code>（整份设备档案）。读写都在同一路径下，返回结构一致。</p>

        ${endpoint(
            "GET", "/v1/apps/{pkg}/config", "读取三块配置；未设置的块为 <code>null</code>。",
            pre(
                "响应",
                """
                {
                  "package": "com.example.app",
                  "proxy": { "enabled": true, "type": "socks5", "host": "1.2.3.4", "port": 1080,
                             "username": "", "password": "", "bypass": "localhost,127.0.0.1" },
                  "gaid": { "gaid": "38400000-8cf0-11bd-b23e-10b96e40000d", "limitAdTracking": false },
                  "device": { "brand": "samsung", "model": "SM-S938B", "...": "..." }
                }
                """.trimIndent()
            )
        )}

        ${endpoint(
            "POST", "/v1/apps/{pkg}/config",
            "写入配置。<b>只处理请求里出现的块</b>：不传的块保持原值，显式传 <code>null</code> 则清除该块。",
            params(
                Param("proxy", "object|null", "否", "代理配置，结构见「数据模型 · Proxy」"),
                Param("gaid", "object|null", "否", "广告 ID，结构见「数据模型 · GAID」"),
                Param("device", "object|null", "否", "设备档案，字段见「数据模型 · Device」")
            ) + pre(
                "请求（只改设备档案，代理保持不动）",
                """
                {
                  "device": {
                    "brand": "vivo", "model": "V2318A", "manufacturer": "vivo",
                    "osVersion": "14", "apiLevel": "34",
                    "displayWidth": "1260", "displayHeight": "2800", "screenDensityNumber": "453",
                    "androidId": "0f20511218377cda", "mcc": 515, "mnc": 66,
                    "carrier": "DITO", "countryCode": "PH", "locale": "en_PH",
                    "timezone": "Asia/Manila", "cpuAbi": "arm64-v8a",
                    "gpuRenderer": "Adreno (TM) 642L", "gpuVendor": "Qualcomm"
                  }
                }
                """.trimIndent()
            ) + pre(
                "清除代理",
                "{\"proxy\": null}"
            )
        )}
    """.trimIndent()
    )

    private fun tasks(base: String) = section(
        "tasks", "任务", """
        <p>任务是「一串步骤」，队列串行执行，可跨进程重启恢复。单 job 最多 50 步，队列上限 500，
        历史保留 200 条。</p>

        ${endpoint(
            "POST", "/v1/tasks",
            "提交任务。支持三种写法：<code>steps</code> 数组、单步 <code>type</code>、或 <code>tasks</code> 批量。",
            params(
                Param("steps", "array", "三选一", "步骤数组，按顺序执行"),
                Param("type", "string", "三选一", "单步任务，其余同名字段作为该步参数"),
                Param("tasks", "array", "三选一", "批量提交，每个元素是一个 job"),
                Param("name", "string", "否", "任务名，仅用于展示"),
                Param("stopOnError", "bool", "否", "默认 <code>true</code>：某步失败即终止后续步骤")
            ) + pre(
                "请求（多步）",
                """
                {
                  "name": "install and run",
                  "steps": [
                    { "type": "install", "url": "https://example.com/app.apk" },
                    { "type": "config", "package": "com.example.app",
                      "proxy": { "type": "socks5", "host": "1.2.3.4", "port": 1080 },
                      "gaid": { "gaid": "38400000-8cf0-11bd-b23e-10b96e40000d" } },
                    { "type": "launch", "package": "com.example.app" },
                    { "type": "wait", "seconds": 30 }
                  ]
                }
                """.trimIndent()
            ) + pre(
                "响应",
                """
                {
                  "tasks": [ { "id": "a1b2c3", "state": "QUEUED", "steps": 4 } ],
                  "counts": { "queued": 1, "running": 0, "succeeded": 0, "failed": 0 }
                }
                """.trimIndent()
            )
        )}

        ${endpoint(
            "GET", "/v1/tasks", "按状态过滤任务列表，新的在前。",
            params(
                Param("state", "string", "否", "QUEUED / RUNNING / SUCCEEDED / FAILED / CANCELED / SKIPPED"),
                Param("limit", "int", "否", "默认 50，范围 1~200")
            ) + pre(
                "响应",
                """
                {
                  "tasks": [ { "id": "a1b2c3", "name": "install and run", "state": "RUNNING",
                               "currentStep": 1, "steps": [ ... ] } ],
                  "counts": { "queued": 0, "running": 1, "succeeded": 3, "failed": 1 }
                }
                """.trimIndent()
            )
        )}

        ${endpoint(
            "GET", "/v1/tasks/{id}", "查询单个任务：含每一步的 state / costMs / error。",
            pre(
                "响应",
                """
                {
                  "id": "a1b2c3",
                  "name": "install and run",
                  "stopOnError": true,
                  "state": "SUCCEEDED",
                  "currentStep": 3,
                  "createdAt": 1762000000000,
                  "startedAt": 1762000000100,
                  "finishedAt": 1762000032000,
                  "steps": [
                    { "type": "install", "state": "SUCCEEDED", "costMs": 8200 },
                    { "type": "wait", "seconds": 30, "state": "SUCCEEDED", "costMs": 30004 }
                  ]
                }
                """.trimIndent()
            )
        )}

        ${endpoint(
            "POST", "/v1/tasks/{id}/cancel", "取消排队中或运行中的任务（运行中的会中断当前步骤）。",
            pre("响应", "{\"success\":true}") + pre("任务不存在", "{\"success\":false,\"error\":\"...\"}（404）")
        )}

        ${endpoint(
            "POST", "/v1/tasks/clear", "清除已结束的任务记录。",
            params(Param("state", "string", "否", "只清除该状态；不传清所有已结束的")) +
                    pre("响应", "{\"success\":true,\"removed\":3}")
        )}

        ${endpoint(
            "POST", "/v1/tasks/stop", "取消所有排队任务并中断当前运行任务。",
            pre("响应", "{\"success\":true,\"count\":2}")
        )}

        ${endpoint(
            "GET", "/v1/tasks/log", "增量读取引擎日志（环形缓冲，最多 200 行）。",
            params(Param("since", "int", "否", "上次返回的 since，首次传 0")) +
                    pre("响应", "{\"since\":128,\"lines\":[\"[12:00:01] job a1b2c3 queued\"]}")
        )}
    """.trimIndent()
    )

    private fun taskMode(base: String) = section(
        "taskmode", "任务模式", """
        <p>任务模式开启后，宿主界面显示任务控制台（计数、队列、PAUSE / STOP ALL / CLEAR）。
        暂停只影响「取下一个排队任务」，已经在跑的步骤不会中断。</p>

        ${endpoint(
            "GET", "/v1/task-mode", "读取开关、暂停状态与计数。",
            pre(
                "响应",
                """
                {
                  "enabled": true,
                  "paused": false,
                  "port": 8080,
                  "counts": { "queued": 0, "running": 0, "succeeded": 5, "failed": 1 }
                }
                """.trimIndent()
            )
        )}

        ${endpoint(
            "POST", "/v1/task-mode", "修改开关 / 暂停状态，返回修改后的完整状态。",
            params(
                Param("enabled", "bool", "否", "打开 / 关闭任务模式（控制台随开关显示）"),
                Param("paused", "bool", "否", "暂停 / 恢复队列消费")
            ) + pre("请求", "{\"enabled\": true, \"paused\": false}")
        )}
    """.trimIndent()
    )

    private fun models() = section(
        "models", "数据模型", """
        <h3 id="model-proxy">Proxy</h3>
        ${params(
            Param("enabled", "bool", "否", "默认 <code>true</code>"),
            Param("type", "string", "否", "<code>http</code>（默认）或 <code>socks5</code>"),
            Param("host", "string", "是", "代理地址"),
            Param("port", "int", "是", "代理端口"),
            Param("username", "string", "否", "认证用户名"),
            Param("password", "string", "否", "认证密码"),
            Param("bypass", "string", "否", "默认 <code>localhost,127.0.0.1</code>")
        )}
        ${pre(
            "示例",
            """
            { "enabled": true, "type": "socks5", "host": "gate.example.com", "port": 58688,
              "username": "user", "password": "pass", "bypass": "localhost,127.0.0.1" }
            """.trimIndent()
        )}

        <h3 id="model-gaid">GAID</h3>
        ${params(
            Param("gaid", "string", "是", "UUID 形式的广告 ID"),
            Param("limitAdTracking", "bool", "否", "默认 <code>false</code>；为 true 时上报限制广告跟踪")
        )}

        <h3 id="model-device">Device</h3>
        <p>整份档案原样保存：容器认识的键会真正落到系统 API，不认识的键也会保留（供上报使用）。
        字段名同时接受短名（<code>width</code>/<code>dpi</code>）与 emu 生成器的长名
        （<code>displayWidth</code>/<code>screenDensityNumber</code>）。常用字段：</p>
        ${params(
            Param("brand / model / manufacturer", "string", "-", "Build.BRAND / MODEL / MANUFACTURER"),
            Param("device / product / hardwareName", "string", "-", "Build.DEVICE / PRODUCT / HARDWARE（真机档案取代号）"),
            Param("osVersion / apiLevel", "string", "-", "Build.VERSION.RELEASE / SDK_INT"),
            Param("buildName / buildDisplayId / buildType", "string", "-", "Build.ID / DISPLAY / TYPE"),
            Param("displayWidth / displayHeight / screenDensityNumber", "int", "-", "分辨率与 dpi，同时影响 Configuration"),
            Param("screenSize / screenFormat / orientation", "string", "-", "small|normal|large、long|notlong、portrait|landscape"),
            Param("language / locale / countryCode / timezone", "string", "-", "Locale 与 TimeZone.setDefault"),
            Param("mcc / mnc / carrier / operator", "string|int", "-", "运营商与双卡信息；mcc=0 表示无 SIM"),
            Param("imei1 / imei2 / imsi / iccid / meid", "string", "-", "显式空串 = 该设备没有此标识"),
            Param("androidId", "string", "-", "Settings.Secure android_id（16 位十六进制）"),
            Param("mac / ssid / bssid", "string", "-", "WifiInfo"),
            Param("cpuAbi / cpuAbi2", "string", "-", "Build.SUPPORTED_ABIS"),
            Param("cpuDetails", "string", "-", "/proc/cpuinfo 全文"),
            Param("totalMemory / availableMemory", "long", "-", "ActivityManager.MemoryInfo"),
            Param("totalInternalStorage / availableInternalStorage", "long", "-", "StatFs"),
            Param("batteryLevel / isOnCharging", "string", "-", "BatteryManager sticky 广播"),
            Param("systemVolume", "int", "-", "AudioManager 媒体音量"),
            Param("gpuRenderer / gpuVendor", "string", "-", "GLES20.glGetString 的 RENDERER / VENDOR"),
            Param("userAgentWebView / Browser / App / Dalvik", "string", "-", "只进上报 payload，不改变系统字段")
        )}
        ${pre(
            "示例",
            """
            {
              "brand": "samsung", "model": "SM-S938B", "manufacturer": "samsung",
              "device": "e2q", "product": "e2qxxx",
              "osVersion": "15", "apiLevel": "35", "buildName": "AP3A.240905.015",
              "displayWidth": "1440", "displayHeight": "3120", "screenDensityNumber": "560",
              "androidId": "0123456789abcdef",
              "mcc": 310, "mnc": 260, "carrier": "T-Mobile", "countryCode": "US",
              "locale": "en_US", "timezone": "America/New_York",
              "cpuAbi": "arm64-v8a",
              "gpuRenderer": "Adreno (TM) 750", "gpuVendor": "Qualcomm"
            }
            """.trimIndent()
        )}

        <h3 id="model-job">TaskJob</h3>
        ${params(
            Param("id", "string", "-", "任务 id，提交后由容器生成"),
            Param("name", "string", "-", "任务名"),
            Param("stopOnError", "bool", "-", "失败是否终止后续步骤，默认 true"),
            Param("state", "string", "-", "QUEUED / RUNNING / SUCCEEDED / FAILED / CANCELED / SKIPPED"),
            Param("currentStep", "int", "-", "当前步骤下标，-1 表示未开始"),
            Param("createdAt / startedAt / finishedAt", "long", "-", "毫秒时间戳，未发生为 0"),
            Param("error", "string", "-", "失败原因（仅失败时出现）"),
            Param("steps", "array", "-", "步骤数组，结构见下")
        )}

        <h3 id="model-step">TaskStep</h3>
        ${params(
            Param("type", "string", "是", "install / config / launch / stop / uninstall / wait"),
            Param("state", "string", "-", "PENDING / RUNNING / SUCCEEDED / FAILED / SKIPPED / CANCELED"),
            Param("costMs", "long", "-", "该步耗时"),
            Param("error", "string", "-", "该步失败原因"),
            Param("timeout", "int", "否", "单步超时秒数，1~3600，默认 install=600 / wait=3600 / 其它=60")
        )}
    """.trimIndent()
    )

    private fun steps() = section(
        "steps", "任务步骤", """
        <p>每步的参数就是「在请求体里平铺的字段」，例如 <code>{"type":"launch","package":"com.x"}</code>。
        未知步骤类型、缺包名、缺下载源等会在提交时直接 400 拒绝。</p>

        ${params(
            Param("install", "-", "-", "<code>url</code> / <code>path</code> / <code>package</code> 三选一；可选 <code>config</code> 安装后立即写入"),
            Param("config", "-", "-", "<code>package</code> 必填；可带 <code>proxy</code> / <code>gaid</code> / <code>device</code>"),
            Param("launch", "-", "-", "<code>package</code> 必填"),
            Param("stop", "-", "-", "<code>package</code> 必填；省略则停全部（建议显式给）"),
            Param("uninstall", "-", "-", "<code>package</code> 必填；同时清理该应用的配置"),
            Param("wait", "-", "-", "<code>seconds</code> 必填，0~3600；可被 <code>POST /v1/tasks/{id}/cancel</code> 打断")
        )}

        <p>状态机：<code>QUEUED → RUNNING → SUCCEEDED | FAILED | CANCELED | SKIPPED</code>。
        <code>stopOnError=false</code> 时，某步失败后续步骤标记为 <code>SKIPPED</code>。</p>
    """.trimIndent()
    )

    private fun examples(base: String, token: String): String {
        val d = "$"
        return section(
        "examples", "完整示例", """
        <p>一次性完成「安装 → 配置 → 启动 → 等待」并轮询结果：</p>
        ${pre(
            "shell",
            """
            TOKEN="$token"
            BASE="$base"

            # 1. 提交任务
            curl -s -X POST "${d}BASE/v1/tasks" \
              -H "X-Api-Token: ${d}TOKEN" -H "Content-Type: application/json" \
              -d '{
                "name": "install and run",
                "steps": [
                  {"type":"install","url":"https://example.com/app.apk"},
                  {"type":"config","package":"com.example.app",
                   "device":{"model":"SM-S938B","brand":"samsung","androidId":"0123456789abcdef"}},
                  {"type":"launch","package":"com.example.app"},
                  {"type":"wait","seconds":20}
                ]
              }'

            # 2. 轮询到结束
            curl -s "${d}BASE/v1/tasks/a1b2c3" -H "X-Api-Token: ${d}TOKEN"

            # 3. 看计数 / 暂停队列
            curl -s "${d}BASE/v1/task-mode" -H "X-Api-Token: ${d}TOKEN"
            curl -s -X POST "${d}BASE/v1/task-mode" -H "X-Api-Token: ${d}TOKEN" \
                 -H "Content-Type: application/json" -d '{"paused":true}'
            """.trimIndent()
        )}
        ${pre(
            "Python",
            """
            import json, urllib.request

            BASE = "$base"
            TOKEN = "$token"

            def call(method, path, body=None):
                data = json.dumps(body).encode() if body is not None else None
                req = urllib.request.Request(BASE + path, data=data, method=method,
                                             headers={"X-Api-Token": TOKEN,
                                                      "Content-Type": "application/json"})
                with urllib.request.urlopen(req) as r:
                    return json.loads(r.read())

            job = call("POST", "/v1/apps/launch", {"package": "com.example.app"})
            print(job)
            """.trimIndent()
        )}
        """.trimIndent()
        )
    }

    // ------------------------------------------------------------------ 页面

    fun html(host: String, port: Int, token: String): String {
        val base = "http://$host:$port"
        val nav = NAV.joinToString("\n") { (id, title) -> "<a href=\"#$id\">$title</a>" }
        return """
            <!doctype html>
            <html lang="zh-CN">
            <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>BlackBox Automation API</title>
            <style>
              :root {
                color-scheme: light dark;
                --fg: #1b1b1f; --muted: #6b7280; --line: #00000014;
                --bg-soft: #00000008; --accent: #3b82f6;
              }
              @media (prefers-color-scheme: dark) {
                :root { --fg: #e6e6ea; --muted: #9aa0a6; --line: #ffffff1f;
                        --bg-soft: #ffffff0f; --accent: #7aa7ff; }
              }
              * { box-sizing: border-box; }
              body { margin: 0; font: 15px/1.65 -apple-system, "Segoe UI", Roboto,
                     "PingFang SC", "Microsoft YaHei", sans-serif; color: var(--fg); }
              header { padding: 28px 32px 16px; border-bottom: 1px solid var(--line); }
              h1 { font-size: 24px; margin: 0 0 6px; }
              .sub { color: var(--muted); font-size: 13px; }
              nav { display: flex; flex-wrap: wrap; gap: 8px; padding: 12px 32px;
                    border-bottom: 1px solid var(--line); position: sticky; top: 0;
                    background: Canvas; z-index: 5; }
              nav a { font-size: 13px; text-decoration: none; color: var(--fg);
                      background: var(--bg-soft); padding: 4px 10px; border-radius: 999px; }
              main { padding: 8px 32px 64px; max-width: 960px; }
              section { padding-top: 8px; }
              h2 { font-size: 19px; margin: 32px 0 12px; padding-bottom: 6px;
                   border-bottom: 1px solid var(--line); }
              h3 { font-size: 15px; margin: 26px 0 8px; font-weight: 600; }
              code { font-family: ui-monospace, SFMono-Regular, Menlo, Consolas, monospace;
                     font-size: 13px; background: var(--bg-soft); padding: 1px 5px; border-radius: 4px; }
              pre { margin: 0; padding: 12px 14px; background: var(--bg-soft); border-radius: 8px;
                    overflow-x: auto; font-size: 12.5px; line-height: 1.55;
                    font-family: ui-monospace, SFMono-Regular, Menlo, Consolas, monospace; }
              .block { margin: 10px 0 16px; }
              .label { font-size: 12px; color: var(--muted); margin-bottom: 4px; }
              table { border-collapse: collapse; width: 100%; margin: 10px 0 16px; font-size: 13.5px; }
              th, td { text-align: left; padding: 7px 10px; border-bottom: 1px solid var(--line);
                       vertical-align: top; }
              th { font-size: 12px; color: var(--muted); font-weight: 600; }
              td.req { white-space: nowrap; color: var(--muted); }
              .badge { display: inline-block; min-width: 46px; text-align: center; font-size: 11px;
                       font-weight: 700; padding: 2px 6px; border-radius: 4px; color: #fff; }
              .m-get { background: #2f855a; } .m-post { background: #2b6cb0; }
              .desc { color: var(--muted); margin: 6px 0 10px; }
              .tip { background: var(--bg-soft); padding: 12px 14px; border-radius: 8px;
                     font-size: 13.5px; margin: 14px 0; }
              a { color: var(--accent); }
            </style>
            </head>
            <body>
            <header>
              <h1>BlackBox Automation API</h1>
              <div class="sub">Base URL <code>$base</code> · version ${ApiServer.VERSION} ·
                token 在「自动化」页可查看与轮换</div>
            </header>
            <nav>$nav</nav>
            <main>
            ${overview(base)}
            ${auth(token)}
            ${health(base)}
            ${apps(base)}
            ${config(base)}
            ${tasks(base)}
            ${taskMode(base)}
            ${models()}
            ${steps()}
            ${examples(base, token)}
            </main>
            </body>
            </html>
        """.trimIndent()
    }
}
