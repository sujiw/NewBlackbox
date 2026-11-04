package top.niunaijun.blackboxa.api

/**
 * 内置 API 文档页：`GET /v1/docs`（也接受 `/`、`/docs`，不校验 token）。
 *
 * 手写 HTML，不引入任何模板引擎 / 静态资源，离线可用。
 */
object ApiDocs {

    private data class Endpoint(val method: String, val path: String, val desc: String)

    private val GROUPS: List<Pair<String, List<Endpoint>>> = listOf(
        "Health" to listOf(
            Endpoint("GET", "/v1/ping", "存活探测，返回 version / port / docs 地址"),
            Endpoint("GET", "/v1/docs", "本页")
        ),
        "Apps" to listOf(
            Endpoint("GET", "/v1/apps", "列出已安装的虚拟应用"),
            Endpoint("POST", "/v1/apps/install", "安装：{url|path|package, config?}"),
            Endpoint("POST", "/v1/apps/launch", "启动：{package}"),
            Endpoint("POST", "/v1/apps/stop", "停止：{package}；省略 package 停止全部"),
            Endpoint("POST", "/v1/apps/uninstall", "卸载：{package}，同时清理 proxy/gaid/device")
        ),
        "Per-app config" to listOf(
            Endpoint("GET", "/v1/apps/{pkg}/config", "读取该应用的 proxy / gaid / device"),
            Endpoint("POST", "/v1/apps/{pkg}/config", "写入：{proxy?, gaid?, device?}，null 表示清除")
        ),
        "Tasks" to listOf(
            Endpoint("POST", "/v1/tasks", "提交任务：{steps:[...]} 或 {type:...} 或 {tasks:[...]}"),
            Endpoint("GET", "/v1/tasks", "查询列表：?state=&limit="),
            Endpoint("GET", "/v1/tasks/{id}", "查询单条任务状态与步骤"),
            Endpoint("POST", "/v1/tasks/{id}/cancel", "取消排队 / 运行中的任务"),
            Endpoint("POST", "/v1/tasks/clear", "清除已结束任务：?state="),
            Endpoint("POST", "/v1/tasks/stop", "取消排队 + 运行中的全部任务"),
            Endpoint("GET", "/v1/tasks/log", "任务日志：?since=")
        ),
        "Task mode" to listOf(
            Endpoint("GET", "/v1/task-mode", "读取 enabled / paused / port / counts"),
            Endpoint("POST", "/v1/task-mode", "设置：{enabled?, paused?}")
        )
    )

    private val DEVICE_EXAMPLE = """
        {
          "device": {
            "brand": "samsung",
            "model": "SM-S938B",
            "manufacturer": "samsung",
            "osVersion": "15",
            "buildName": "AP3A.240905.015",
            "displayWidth": "1440",
            "displayHeight": "3120",
            "screenDensityNumber": "560",
            "androidId": "0123456789abcdef",
            "imei1": "",
            "mcc": 310,
            "mnc": 260,
            "carrier": "T-Mobile",
            "countryCode": "US",
            "locale": "en_US",
            "timezone": "America/New_York",
            "cpuAbi": "arm64-v8a",
            "gpuRenderer": "Adreno (TM) 750",
            "gpuVendor": "Qualcomm"
          }
        }
    """.trimIndent()

    fun html(host: String, port: Int, token: String): String {
        val base = "http://$host:$port"
        val rows = GROUPS.joinToString("\n") { (group, endpoints) ->
            val body = endpoints.joinToString("\n") { e ->
                "<tr><td class=\"m\">${e.method}</td>" +
                        "<td class=\"p\">${e.path}</td>" +
                        "<td>${e.desc}</td></tr>"
            }
            "<h2>$group</h2>\n<table>\n<tr><th>方法</th><th>路径</th><th>说明</th></tr>\n$body\n</table>"
        }
        val curlExample = """
            curl -s -H "X-Api-Token: $token" $base/v1/apps
            curl -s -X POST -H "X-Api-Token: $token" -H "Content-Type: application/json" \
                 -d '{"package":"com.example.app"}' $base/v1/apps/launch
        """.trimIndent()
        return """
            <!doctype html>
            <html lang="zh-CN">
            <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>BlackBox Automation API</title>
            <style>
              :root { color-scheme: light dark; }
              body { font: 14px/1.6 -apple-system, "Segoe UI", Roboto, "PingFang SC", sans-serif;
                     margin: 0; padding: 24px; max-width: 920px; }
              h1 { font-size: 22px; margin: 0 0 4px; }
              h2 { font-size: 16px; margin: 28px 0 8px; }
              .sub { color: #888; margin-bottom: 20px; }
              table { border-collapse: collapse; width: 100%; }
              th, td { text-align: left; padding: 6px 10px; border-bottom: 1px solid #8883; vertical-align: top; }
              th { font-weight: 600; color: #888; font-size: 12px; text-transform: uppercase; }
              td.m { width: 70px; font-weight: 600; }
              td.p, code, pre { font-family: ui-monospace, SFMono-Regular, Menlo, monospace; }
              td.p { white-space: nowrap; }
              pre { background: #8881; padding: 12px; border-radius: 8px; overflow-x: auto; }
              .tip { background: #8881; padding: 12px; border-radius: 8px; margin: 16px 0; }
              a { color: #4c8dff; }
            </style>
            </head>
            <body>
            <h1>BlackBox Automation API</h1>
            <div class="sub">Base URL <code>$base</code> · 版本 ${ApiServer.VERSION}</div>

            <div class="tip">
              所有请求都要带 <code>X-Api-Token</code> 头（除本页）：<br>
              <code>$token</code>
            </div>

            $rows

            <h2>示例</h2>
            <pre>$curlExample</pre>

            <h2>设备档案（device）</h2>
            <p>字段与原样下发，未识别的键会原样保存；emu 生成器产出的整份档案可直接使用。</p>
            <pre>${DEVICE_EXAMPLE.replace("<", "&lt;")}</pre>
            </body>
            </html>
        """.trimIndent()
    }
}
