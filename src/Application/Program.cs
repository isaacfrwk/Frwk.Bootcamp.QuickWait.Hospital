using FrwkBootCampQuickWait.Hospital.Application;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

namespace NRInvest.Api
{
    public static class Program
    {
        public static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run();
        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .UseStartup<Startup>();


        public static IHostBuilder UseStartup<TStartup>(
        this IHostBuilder hostBuilder) where TStartup : class
        {
            // Invoke the ConfigureServices method on IHostBuilder...
            hostBuilder.ConfigureServices((ctx, serviceCollection) =>
            {
                // Find a method that has this signature: ConfigureServices(IServiceCollection)
                var cfgServicesMethod = typeof(TStartup).GetMethod(
                    "ConfigureServices", new Type[] { typeof(IServiceCollection) });

                // Check if TStartup has a ctor that takes a IConfiguration parameter
                var hasConfigCtor = typeof(TStartup).GetConstructor(
                    new Type[] { typeof(IConfiguration) }) != null;

                // create a TStartup instance based on ctor
                var startUpObj = hasConfigCtor ?
                    (TStartup)Activator.CreateInstance(typeof(TStartup), ctx.Configuration) :
                    (TStartup)Activator.CreateInstance(typeof(TStartup), null);

                cfgServicesMethod?.Invoke(startUpObj, new object[] { serviceCollection });
            });

            return hostBuilder;
        }
    }
}