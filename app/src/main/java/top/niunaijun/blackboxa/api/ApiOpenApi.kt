package top.niunaijun.blackboxa.api

/**
 * OpenAPI 3.0 描述文件：`GET /v1/openapi.json`（也接受 `/openapi.json`，不校验 token）。
 *
 * 与 [ApiDocs] 的手写页面同源：路径、参数、schema 都对应 ApiServer / ConfigOps / TaskEngine
 * 的实际实现，可直接导入 Postman / Insomnia / Swagger UI / Apifox。
 */
object ApiOpenApi {

    fun json(baseUrl: String): String = TEMPLATE.replace("__BASE__", baseUrl)

    private val TEMPLATE = """
{
  "openapi": "3.0.3",
  "info": {
    "title": "BlackBox Automation API",
    "version": "1",
    "description": "BlackBox 内置的本地 REST 服务：在虚拟空间里安装 / 启动应用，并给每个应用注入代理、Google 广告 ID 与整份设备档案。任务串行执行，可跨进程重启恢复。"
  },
  "servers": [
    { "url": "__BASE__", "description": "本机容器" }
  ],
  "tags": [
    { "name": "Health", "description": "存活探测与文档" },
    { "name": "Apps", "description": "虚拟应用的安装 / 启动 / 停止 / 卸载" },
    { "name": "Config", "description": "每个应用的代理、广告 ID 与设备档案" },
    { "name": "Tasks", "description": "串行任务队列" },
    { "name": "Task mode", "description": "任务模式开关与暂停" }
  ],
  "security": [ { "ApiToken": [] } ],
  "paths": {
    "/v1/ping": {
      "get": {
        "tags": ["Health"],
        "summary": "存活探测",
        "description": "返回版本、端口与文档地址。",
        "security": [],
        "responses": {
          "200": {
            "description": "服务正常",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/PingResponse" }
              }
            }
          }
        }
      }
    },
    "/v1/docs": {
      "get": {
        "tags": ["Health"],
        "summary": "内置文档页",
        "description": "离线 HTML 文档，不需要 token。`/` 与 `/docs` 等价。",
        "security": [],
        "responses": {
          "200": {
            "description": "HTML 文档",
            "content": { "text/html": { "schema": { "type": "string" } } }
          }
        }
      }
    },
    "/v1/openapi.json": {
      "get": {
        "tags": ["Health"],
        "summary": "本描述文件",
        "description": "OpenAPI 3.0 JSON，不需要 token。`/openapi.json` 等价。",
        "security": [],
        "responses": {
          "200": {
            "description": "OpenAPI 文档",
            "content": { "application/json": { "schema": { "type": "object" } } }
          }
        }
      }
    },
    "/v1/apps": {
      "get": {
        "tags": ["Apps"],
        "summary": "列出已安装应用",
        "responses": {
          "200": {
            "description": "已安装的包名列表",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/AppList" }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" }
        }
      }
    },
    "/v1/apps/install": {
      "post": {
        "tags": ["Apps"],
        "summary": "安装应用（默认先卸后装）",
        "description": "url / path / package 三选一，按该优先级取第一个非空值。reinstall=true（默认）且目标包已存在时，先卸载（同时清除旧配置）再安装；安装成功后可选地立即写入配置。",
        "requestBody": {
          "required": true,
          "content": {
            "application/json": {
              "schema": { "${'$'}ref": "#/components/schemas/InstallRequest" },
              "examples": {
                "url": {
                  "summary": "远程下载安装并写配置",
                  "value": {
                    "url": "https://example.com/app.apk",
                    "config": {
                      "proxy": { "enabled": true, "type": "socks5", "host": "1.2.3.4", "port": 1080 },
                      "device": { "model": "SM-S938B", "brand": "samsung" }
                    }
                  }
                }
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "安装结果（success=false 时看 message）",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/InstallResponse" }
              }
            }
          },
          "400": { "${'$'}ref": "#/components/responses/BadRequest" },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" },
          "500": { "${'$'}ref": "#/components/responses/ServerError" }
        }
      }
    },
    "/v1/apps/launch": {
      "post": {
        "tags": ["Apps"],
        "summary": "启动应用",
        "requestBody": {
          "required": true,
          "content": {
            "application/json": {
              "schema": { "${'$'}ref": "#/components/schemas/PackageRequest" }
            }
          }
        },
        "responses": {
          "200": {
            "description": "启动结果",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/PackageResult" }
              }
            }
          },
          "400": { "${'$'}ref": "#/components/responses/BadRequest" },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" },
          "500": { "${'$'}ref": "#/components/responses/ServerError" }
        }
      }
    },
    "/v1/apps/stop": {
      "post": {
        "tags": ["Apps"],
        "summary": "停止应用",
        "description": "省略 package 时停止所有正在运行的虚拟应用。",
        "requestBody": {
          "required": false,
          "content": {
            "application/json": {
              "schema": {
                "type": "object",
                "properties": { "package": { "type": "string" } }
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "实际被停止的包名",
            "content": {
              "application/json": {
                "schema": {
                  "type": "object",
                  "properties": {
                    "success": { "type": "boolean" },
                    "stopped": { "type": "array", "items": { "type": "string" } }
                  }
                }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" }
        }
      }
    },
    "/v1/apps/uninstall": {
      "post": {
        "tags": ["Apps"],
        "summary": "卸载应用",
        "description": "先停止再卸载，并清除该应用保存的 proxy / gaid / device 配置。",
        "requestBody": {
          "required": true,
          "content": {
            "application/json": {
              "schema": { "${'$'}ref": "#/components/schemas/PackageRequest" }
            }
          }
        },
        "responses": {
          "200": {
            "description": "卸载结果",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/PackageResult" }
              }
            }
          },
          "400": { "${'$'}ref": "#/components/responses/BadRequest" },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" },
          "500": { "${'$'}ref": "#/components/responses/ServerError" }
        }
      }
    },
    "/v1/apps/{pkg}/clear": {
      "post": {
        "tags": ["Apps"],
        "summary": "清空应用数据（等同重装）",
        "description": "先停止应用，再删除 data / data_de / 外部存储 Android/data 目录；config=true（默认）时同时清除 proxy / gaid / device。",
        "parameters": [
          {
            "name": "pkg",
            "in": "path",
            "required": true,
            "schema": { "type": "string" },
            "description": "目标应用包名"
          }
        ],
        "requestBody": {
          "required": false,
          "content": {
            "application/json": {
              "schema": { "${'$'}ref": "#/components/schemas/ClearRequest" }
            }
          }
        },
        "responses": {
          "200": {
            "description": "被删除的目录列表",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/ClearResponse" }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" },
          "405": { "${'$'}ref": "#/components/responses/BadRequest" }
        }
      }
    },
    "/v1/apps/{pkg}/config": {
      "parameters": [
        {
          "name": "pkg",
          "in": "path",
          "required": true,
          "schema": { "type": "string" },
          "description": "目标应用包名"
        }
      ],
      "get": {
        "tags": ["Config"],
        "summary": "读取应用配置",
        "responses": {
          "200": {
            "description": "三块配置，未设置为 null",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/ConfigResponse" }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" }
        }
      },
      "post": {
        "tags": ["Config"],
        "summary": "写入应用配置",
        "description": "只处理请求里出现的块：不传的块保持原值，显式传 null 则清除该块。",
        "requestBody": {
          "required": true,
          "content": {
            "application/json": {
              "schema": { "${'$'}ref": "#/components/schemas/ConfigUpdate" },
              "examples": {
                "deviceOnly": {
                  "summary": "只改设备档案",
                  "value": {
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
                },
                "clearProxy": {
                  "summary": "清除代理",
                  "value": { "proxy": null }
                }
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "写入后的完整配置",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/ConfigResponse" }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" }
        }
      }
    },
    "/v1/tasks": {
      "get": {
        "tags": ["Tasks"],
        "summary": "任务列表",
        "parameters": [
          {
            "name": "state",
            "in": "query",
            "required": false,
            "schema": { "${'$'}ref": "#/components/schemas/JobState" }
          },
          {
            "name": "limit",
            "in": "query",
            "required": false,
            "schema": { "type": "integer", "minimum": 1, "maximum": 200, "default": 50 }
          }
        ],
        "responses": {
          "200": {
            "description": "任务列表与计数",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/TaskList" }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" }
        }
      },
      "post": {
        "tags": ["Tasks"],
        "summary": "提交任务",
        "description": "支持三种写法：steps 数组、单步 type、tasks 批量。单个 job 最多 50 步。",
        "requestBody": {
          "required": true,
          "content": {
            "application/json": {
              "schema": { "${'$'}ref": "#/components/schemas/TaskSubmitRequest" },
              "examples": {
                "steps": {
                  "summary": "安装 → 配置 → 启动 → 等待",
                  "value": {
                    "name": "install and run",
                    "steps": [
                      { "type": "install", "url": "https://example.com/app.apk" },
                      {
                        "type": "config", "package": "com.example.app",
                        "proxy": { "type": "socks5", "host": "1.2.3.4", "port": 1080 },
                        "gaid": { "gaid": "38400000-8cf0-11bd-b23e-10b96e40000d" }
                      },
                      { "type": "launch", "package": "com.example.app" },
                      { "type": "wait", "seconds": 30 }
                    ]
                  }
                },
                "single": {
                  "summary": "单步任务",
                  "value": { "type": "launch", "package": "com.example.app" }
                }
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "已入队的任务",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/TaskSubmitResponse" }
              }
            }
          },
          "400": { "${'$'}ref": "#/components/responses/BadRequest" },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" },
          "500": { "${'$'}ref": "#/components/responses/ServerError" }
        }
      }
    },
    "/v1/tasks/{id}": {
      "get": {
        "tags": ["Tasks"],
        "summary": "任务详情",
        "parameters": [
          { "name": "id", "in": "path", "required": true, "schema": { "type": "string" } }
        ],
        "responses": {
          "200": {
            "description": "任务详情（含每步状态）",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/TaskJob" }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" },
          "404": { "${'$'}ref": "#/components/responses/NotFound" }
        }
      }
    },
    "/v1/tasks/{id}/cancel": {
      "post": {
        "tags": ["Tasks"],
        "summary": "取消任务",
        "description": "排队中的直接取消；运行中的会中断当前步骤。",
        "parameters": [
          { "name": "id", "in": "path", "required": true, "schema": { "type": "string" } }
        ],
        "responses": {
          "200": {
            "description": "取消结果",
            "content": {
              "application/json": {
                "schema": {
                  "type": "object",
                  "properties": { "success": { "type": "boolean" } }
                }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" },
          "404": { "${'$'}ref": "#/components/responses/NotFound" }
        }
      }
    },
    "/v1/tasks/clear": {
      "post": {
        "tags": ["Tasks"],
        "summary": "清除已结束任务",
        "parameters": [
          {
            "name": "state",
            "in": "query",
            "required": false,
            "schema": { "${'$'}ref": "#/components/schemas/JobState" }
          }
        ],
        "responses": {
          "200": {
            "description": "清除数量",
            "content": {
              "application/json": {
                "schema": {
                  "type": "object",
                  "properties": {
                    "success": { "type": "boolean" },
                    "removed": { "type": "integer" }
                  }
                }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" }
        }
      }
    },
    "/v1/tasks/stop": {
      "post": {
        "tags": ["Tasks"],
        "summary": "停止全部任务",
        "description": "取消排队任务并中断当前运行任务。",
        "responses": {
          "200": {
            "description": "受影响数量",
            "content": {
              "application/json": {
                "schema": {
                  "type": "object",
                  "properties": {
                    "success": { "type": "boolean" },
                    "count": { "type": "integer" }
                  }
                }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" }
        }
      }
    },
    "/v1/tasks/log": {
      "get": {
        "tags": ["Tasks"],
        "summary": "引擎日志",
        "description": "环形缓冲，最多 200 行，按 since 增量读取。",
        "parameters": [
          {
            "name": "since",
            "in": "query",
            "required": false,
            "schema": { "type": "integer", "default": 0 }
          }
        ],
        "responses": {
          "200": {
            "description": "日志增量",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/TaskLog" }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" }
        }
      }
    },
    "/v1/task-mode": {
      "get": {
        "tags": ["Task mode"],
        "summary": "读取任务模式",
        "responses": {
          "200": {
            "description": "开关、暂停与计数",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/TaskMode" }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" }
        }
      },
      "post": {
        "tags": ["Task mode"],
        "summary": "修改任务模式",
        "description": "只处理出现的字段；返回修改后的完整状态。",
        "requestBody": {
          "required": true,
          "content": {
            "application/json": {
              "schema": { "${'$'}ref": "#/components/schemas/TaskModeUpdate" }
            }
          }
        },
        "responses": {
          "200": {
            "description": "修改后的状态",
            "content": {
              "application/json": {
                "schema": { "${'$'}ref": "#/components/schemas/TaskMode" }
              }
            }
          },
          "401": { "${'$'}ref": "#/components/responses/Unauthorized" }
        }
      }
    }
  },
  "components": {
    "securitySchemes": {
      "ApiToken": {
        "type": "apiKey",
        "in": "header",
        "name": "X-Api-Token",
        "description": "「自动化」页显示的 token，可轮换。文档页与 openapi.json 不需要。"
      }
    },
    "responses": {
      "BadRequest": {
        "description": "参数缺失或类型不对",
        "content": {
          "application/json": { "schema": { "${'$'}ref": "#/components/schemas/Error" } }
        }
      },
      "Unauthorized": {
        "description": "token 缺失或不匹配",
        "content": {
          "application/json": { "schema": { "${'$'}ref": "#/components/schemas/Error" } }
        }
      },
      "NotFound": {
        "description": "路径或任务不存在",
        "content": {
          "application/json": { "schema": { "${'$'}ref": "#/components/schemas/Error" } }
        }
      },
      "ServerError": {
        "description": "容器内部异常",
        "content": {
          "application/json": { "schema": { "${'$'}ref": "#/components/schemas/Error" } }
        }
      }
    },
    "schemas": {
      "Error": {
        "type": "object",
        "required": ["success", "error"],
        "properties": {
          "success": { "type": "boolean", "enum": [false] },
          "error": { "type": "string" }
        }
      },
      "Counts": {
        "type": "object",
        "properties": {
          "queued": { "type": "integer" },
          "running": { "type": "integer" },
          "succeeded": { "type": "integer" },
          "failed": { "type": "integer" }
        }
      },
      "PingResponse": {
        "type": "object",
        "properties": {
          "ok": { "type": "boolean" },
          "version": { "type": "integer" },
          "port": { "type": "integer" },
          "docs": { "type": "string", "example": "/v1/docs" }
        }
      },
      "AppList": {
        "type": "object",
        "properties": {
          "apps": {
            "type": "array",
            "items": {
              "type": "object",
              "properties": { "package": { "type": "string" } }
            }
          }
        }
      },
      "PackageRequest": {
        "type": "object",
        "required": ["package"],
        "properties": { "package": { "type": "string", "example": "com.example.app" } }
      },
      "PackageResult": {
        "type": "object",
        "properties": {
          "success": { "type": "boolean" },
          "package": { "type": "string" }
        }
      },
      "InstallRequest": {
        "type": "object",
        "properties": {
          "url": { "type": "string", "description": "远程地址，容器内下载后安装" },
          "path": { "type": "string", "description": "容器可见路径，如 /sdcard/a.apk" },
          "package": { "type": "string", "description": "设备上已安装的同包名应用；设备上没有则用容器内已安装的 APK 重装" },
          "reinstall": {
            "type": "boolean",
            "default": true,
            "description": "目标包已存在时是否先卸载再安装"
          },
          "referrer": {
            "description": "Play 安装来源：裸串（adjust_reftag=...）、商店链接（自动取 referrer 参数）或 ReferrerConfig 对象",
            "oneOf": [
              { "type": "string" },
              { "${'$'}ref": "#/components/schemas/ReferrerConfig" }
            ]
          },
          "config": { "${'$'}ref": "#/components/schemas/ConfigUpdate" }
        }
      },
      "InstallResponse": {
        "type": "object",
        "properties": {
          "success": { "type": "boolean" },
          "package": { "type": "string" },
          "message": { "type": "string" },
          "reinstalled": { "type": "boolean", "description": "本次是否执行了先卸载旧版本" },
          "referrer": { "type": "string", "nullable": true, "description": "本次写入的 install referrer" },
          "config": { "${'$'}ref": "#/components/schemas/ConfigResponse" }
        }
      },
      "ProxyConfig": {
        "type": "object",
        "properties": {
          "enabled": { "type": "boolean", "default": true },
          "type": { "type": "string", "enum": ["http", "socks5"], "default": "http" },
          "host": { "type": "string" },
          "port": { "type": "integer" },
          "username": { "type": "string" },
          "password": { "type": "string" },
          "bypass": { "type": "string", "default": "localhost,127.0.0.1" }
        }
      },
      "GaidConfig": {
        "type": "object",
        "properties": {
          "gaid": { "type": "string", "format": "uuid" },
          "limitAdTracking": { "type": "boolean", "default": false }
        }
      },
      "DeviceProfile": {
        "type": "object",
        "description": "整份设备档案原样保存：容器认识的键落到系统 API，未知键保留供上报。字段名同时接受短名与 emu 长名。",
        "additionalProperties": true,
        "properties": {
          "brand": { "type": "string" },
          "model": { "type": "string" },
          "manufacturer": { "type": "string" },
          "device": { "type": "string" },
          "product": { "type": "string" },
          "hardwareName": { "type": "string" },
          "osVersion": { "type": "string" },
          "apiLevel": { "type": "string" },
          "buildName": { "type": "string" },
          "buildDisplayId": { "type": "string" },
          "buildType": { "type": "string" },
          "displayWidth": { "type": "integer" },
          "displayHeight": { "type": "integer" },
          "screenDensityNumber": { "type": "integer" },
          "screenSize": { "type": "string", "enum": ["small", "normal", "large", "xlarge"] },
          "screenFormat": { "type": "string", "enum": ["long", "notlong"] },
          "orientation": { "type": "string", "enum": ["portrait", "landscape"] },
          "language": { "type": "string" },
          "locale": { "type": "string" },
          "countryCode": { "type": "string" },
          "timezone": { "type": "string" },
          "mcc": { "type": "integer", "description": "0 表示无 SIM" },
          "mnc": { "type": "integer" },
          "carrier": { "type": "string" },
          "operator": { "type": "string" },
          "imei1": { "type": "string", "description": "空串表示该设备没有此标识" },
          "imei2": { "type": "string" },
          "imsi": { "type": "string" },
          "iccid": { "type": "string" },
          "meid": { "type": "string" },
          "androidId": { "type": "string" },
          "mac": { "type": "string" },
          "ssid": { "type": "string" },
          "bssid": { "type": "string" },
          "cpuAbi": { "type": "string" },
          "cpuAbi2": { "type": "string" },
          "cpuDetails": { "type": "string", "description": "/proc/cpuinfo 全文" },
          "totalMemory": { "type": "integer" },
          "availableMemory": { "type": "integer" },
          "totalInternalStorage": { "type": "integer" },
          "availableInternalStorage": { "type": "integer" },
          "batteryLevel": { "type": "string" },
          "isOnCharging": { "type": "string", "enum": ["yes", "no"] },
          "systemVolume": { "type": "integer" },
          "gpuRenderer": { "type": "string" },
          "gpuVendor": { "type": "string" },
          "gpuVersion": { "type": "string", "description": "只用于上报 payload，不修改 GL_VERSION" },
          "userAgentWebView": { "type": "string" },
          "userAgentBrowser": { "type": "string" },
          "userAgentApp": { "type": "string" },
          "userAgentDalvik": { "type": "string" }
        }
      },
      "ClearRequest": {
        "type": "object",
        "properties": {
          "config": {
            "type": "boolean",
            "default": true,
            "description": "是否同时清除该应用保存的 proxy / gaid / device"
          }
        }
      },
      "ClearResponse": {
        "type": "object",
        "properties": {
          "success": { "type": "boolean" },
          "package": { "type": "string" },
          "removed": {
            "type": "array",
            "items": { "type": "string" },
            "description": "实际删除成功的目录绝对路径"
          },
          "configCleared": { "type": "boolean" }
        }
      },
      "ConfigUpdate": {
        "type": "object",
        "description": "只处理出现的块；显式 null 表示清除该块。",
        "properties": {
          "proxy": { "nullable": true, "allOf": [{ "${'$'}ref": "#/components/schemas/ProxyConfig" }] },
          "gaid": { "nullable": true, "allOf": [{ "${'$'}ref": "#/components/schemas/GaidConfig" }] },
          "device": { "nullable": true, "allOf": [{ "${'$'}ref": "#/components/schemas/DeviceProfile" }] },
          "referrer": {
            "nullable": true,
            "oneOf": [
              { "type": "string" },
              { "${'$'}ref": "#/components/schemas/ReferrerConfig" }
            ]
          }
        }
      },
      "ConfigResponse": {
        "type": "object",
        "properties": {
          "package": { "type": "string" },
          "proxy": { "nullable": true, "allOf": [{ "${'$'}ref": "#/components/schemas/ProxyConfig" }] },
          "gaid": { "nullable": true, "allOf": [{ "${'$'}ref": "#/components/schemas/GaidConfig" }] },
          "device": { "nullable": true, "allOf": [{ "${'$'}ref": "#/components/schemas/DeviceProfile" }] },
          "referrer": { "nullable": true, "allOf": [{ "${'$'}ref": "#/components/schemas/ReferrerConfig" }] }
        }
      },
      "ReferrerConfig": {
        "type": "object",
        "required": ["referrer"],
        "properties": {
          "referrer": { "type": "string", "example": "adjust_reftag=cwKyD4chsBr9n" },
          "clickTimestamp": { "type": "integer", "format": "int64", "description": "点击时间（秒）" },
          "installTimestamp": { "type": "integer", "format": "int64", "description": "开始安装时间（秒）" },
          "installVersion": { "type": "string", "description": "安装时商店版本，可选" }
        }
      },
      "JobState": {
        "type": "string",
        "enum": ["QUEUED", "RUNNING", "SUCCEEDED", "FAILED", "CANCELED", "SKIPPED"]
      },
      "StepState": {
        "type": "string",
        "enum": ["PENDING", "RUNNING", "SUCCEEDED", "FAILED", "SKIPPED", "CANCELED"]
      },
      "TaskStep": {
        "type": "object",
        "required": ["type"],
        "properties": {
          "type": {
            "type": "string",
            "enum": ["install", "config", "launch", "stop", "uninstall", "clear", "wait"],
            "description": "install 步骤额外支持 referrer 参数"
          },
          "package": { "type": "string", "description": "config/launch/stop/uninstall 必填" },
          "url": { "type": "string", "description": "install 三选一" },
          "path": { "type": "string", "description": "install 三选一" },
          "config": { "${'$'}ref": "#/components/schemas/ConfigUpdate" },
          "seconds": { "type": "integer", "minimum": 0, "maximum": 3600, "description": "wait 必填" },
          "timeout": {
            "type": "integer",
            "minimum": 1,
            "maximum": 3600,
            "description": "单步超时秒数，默认 install=600 / wait=3600 / 其它=60"
          },
          "state": { "${'$'}ref": "#/components/schemas/StepState" },
          "costMs": { "type": "integer", "readOnly": true },
          "error": { "type": "string", "readOnly": true }
        }
      },
      "TaskJob": {
        "type": "object",
        "properties": {
          "id": { "type": "string" },
          "name": { "type": "string" },
          "stopOnError": { "type": "boolean", "default": true },
          "state": { "${'$'}ref": "#/components/schemas/JobState" },
          "currentStep": { "type": "integer", "description": "-1 表示未开始" },
          "createdAt": { "type": "integer", "format": "int64" },
          "startedAt": { "type": "integer", "format": "int64" },
          "finishedAt": { "type": "integer", "format": "int64" },
          "error": { "type": "string" },
          "steps": { "type": "array", "items": { "${'$'}ref": "#/components/schemas/TaskStep" } }
        }
      },
      "TaskSubmitRequest": {
        "type": "object",
        "properties": {
          "name": { "type": "string" },
          "stopOnError": { "type": "boolean", "default": true },
          "steps": {
            "type": "array",
            "maxItems": 50,
            "items": { "${'$'}ref": "#/components/schemas/TaskStep" }
          },
          "tasks": {
            "type": "array",
            "description": "批量提交，每个元素是 {name, stopOnError, steps}",
            "items": { "${'$'}ref": "#/components/schemas/TaskSubmitRequest" }
          },
          "type": { "type": "string", "description": "单步任务的步骤类型" }
        }
      },
      "TaskSubmitResponse": {
        "type": "object",
        "properties": {
          "tasks": {
            "type": "array",
            "items": {
              "type": "object",
              "properties": {
                "id": { "type": "string" },
                "state": { "${'$'}ref": "#/components/schemas/JobState" },
                "steps": { "type": "integer" }
              }
            }
          },
          "counts": { "${'$'}ref": "#/components/schemas/Counts" }
        }
      },
      "TaskList": {
        "type": "object",
        "properties": {
          "tasks": { "type": "array", "items": { "${'$'}ref": "#/components/schemas/TaskJob" } },
          "counts": { "${'$'}ref": "#/components/schemas/Counts" }
        }
      },
      "TaskLog": {
        "type": "object",
        "properties": {
          "since": { "type": "integer" },
          "lines": { "type": "array", "items": { "type": "string" } }
        }
      },
      "TaskMode": {
        "type": "object",
        "properties": {
          "enabled": { "type": "boolean" },
          "paused": { "type": "boolean" },
          "port": { "type": "integer" },
          "counts": { "${'$'}ref": "#/components/schemas/Counts" }
        }
      },
      "TaskModeUpdate": {
        "type": "object",
        "properties": {
          "enabled": { "type": "boolean" },
          "paused": { "type": "boolean" }
        }
      }
    }
  }
}
    """.trimIndent()
}
