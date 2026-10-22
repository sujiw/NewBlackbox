package top.niunaijun.blackbox.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/ShellUtils.class */
public class ShellUtils {
    public static final String COMMAND_SU = "su";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";

    private ShellUtils() {
        throw new AssertionError();
    }

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static CommandResult execCommand(String command, boolean isRoot) {
        return execCommand(new String[]{command}, isRoot, true);
    }

    public static CommandResult execCommand(List<String> commands, boolean isRoot) {
        return execCommand(commands == null ? null : (String[]) commands.toArray(new String[0]), isRoot, true);
    }

    public static CommandResult execCommand(String[] commands, boolean isRoot) {
        return execCommand(commands, isRoot, true);
    }

    public static CommandResult execCommand(String command, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(new String[]{command}, isRoot, isNeedResultMsg);
    }

    public static CommandResult execCommand(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(commands == null ? null : (String[]) commands.toArray(new String[0]), isRoot, isNeedResultMsg);
    }

    /* JADX WARN: Code duplicated, block: B:52:0x0130  */
    /* JADX WARN: Code duplicated, block: B:66:0x0162  */
    /* JADX WARN: Code duplicated, block: B:79:0x018f  */
    /* JADX WARN: Code duplicated, block: B:84:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:85:0x01a5  */
    public static CommandResult execCommand(String[] commands, boolean isRoot, boolean isNeedResultMsg) {
        String string;
        int result = -1;
        if (commands == null || commands.length == 0) {
            return new CommandResult(-1, null);
        }
        Process process = null;
        BufferedReader successResult = null;
        StringBuilder successMsg = null;
        DataOutputStream os = null;
        try {
            try {
                process = Runtime.getRuntime().exec(isRoot ? COMMAND_SU : COMMAND_SH);
                os = new DataOutputStream(process.getOutputStream());
                for (String command : commands) {
                    if (command != null) {
                        os.write(command.getBytes());
                        os.writeBytes(COMMAND_LINE_END);
                        os.flush();
                    }
                }
                os.writeBytes(COMMAND_EXIT);
                os.flush();
                result = process.waitFor();
                if (isNeedResultMsg) {
                    successMsg = new StringBuilder();
                    successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    while (true) {
                        String s = successResult.readLine();
                        if (s == null) {
                            break;
                        }
                        successMsg.append(s + COMMAND_LINE_END);
                    }
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (successResult != null) {
                    CloseUtils.close(successResult);
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        if (process != null) {
                            process.destroy();
                        }
                        int i = result;
                        if (successMsg == null) {
                            string = null;
                        } else {
                            string = successMsg.toString();
                        }
                        return new CommandResult(i, string);
                    }
                }
                if (successResult != null) {
                    CloseUtils.close(successResult);
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        if (process != null) {
                            process.destroy();
                        }
                        int i2 = result;
                        if (successMsg == null) {
                            string = null;
                        } else {
                            string = successMsg.toString();
                        }
                        return new CommandResult(i2, string);
                    }
                }
                if (successResult != null) {
                    CloseUtils.close(successResult);
                }
                if (process != null) {
                    process.destroy();
                }
            }
            int i3 = result;
            if (successMsg == null) {
                string = null;
            } else {
                string = successMsg.toString();
            }
            return new CommandResult(i3, string);
        } catch (Throwable th) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                    if (process != null) {
                        process.destroy();
                    }
                    throw th;
                }
            }
            if (successResult != null) {
                CloseUtils.close(successResult);
            }
            if (process != null) {
                process.destroy();
            }
            throw th;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/ShellUtils$CommandResult.class */
    public static class CommandResult {
        public int result;
        public String successMsg;

        public CommandResult(int result) {
            this.result = result;
        }

        public CommandResult(int result, String successMsg) {
            this.result = result;
            this.successMsg = successMsg;
        }
    }
}
