using FrwkBootCampQuickWait.Hospital.Application.Extensions;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace FrwkBootCampQuickWait.Hospital.Application
{
    public sealed class Startup
    {
        public IConfiguration Configuration { get; }

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public void ConfigureServices(IServiceCollection services)
        {
            services
                .InjectDomain()
                .InjectHostedServices()
                .InjectSettings(Configuration);
        }
    }
}