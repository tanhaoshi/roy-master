package roy.application.master.oksocket;

public interface IConfiguration {
    /**
     * 修改参数配置
     *
     * @param okOptions 新的参数配置
     * @return 当前的链接管理器
     */
    IConnectionManager option(OkSocketOptions okOptions);

    /**
     * 获得当前连接管理器的参数配置
     *
     * @return 参数配置
     */
    OkSocketOptions getOption();
}
