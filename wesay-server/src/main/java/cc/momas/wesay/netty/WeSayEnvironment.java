package cc.momas.wesay.netty;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 环境变量
 *
 * @author Sod-Momas
 * @since 2021-02-24
 */
public class WeSayEnvironment {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CommandLine cmd;

    public WeSayEnvironment(String[] args) {
        log.debug("args=" + Arrays.toString(args));
        // create Options object
        final Options options = new Options();
        // add t option
        options.addOption("p", "port", true, "The port of server bind.");

        final CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            commandLine = null;
            log.error(e.getMessage());
        }
        cmd = commandLine;
    }

    /**
     * 获取服务器启动的端口，默认为9000
     *
     * @return 服务器启动的端口
     */
    public int getPort() {
        // get value from cmd
        final String port = cmd.getOptionValue('p', "9000");
        return Integer.parseInt(port);
    }
}
