using FrwkBootCampQuickWait.Hospital.Application.Consumers;
using FrwkBootCampQuickWait.Hospital.Domain.Configuration;
using FrwkBootCampQuickWait.Hospital.Domain.Contracts;
using FrwkBootCampQuickWait.Hospital.Domain.Entities;
using FrwkBootCampQuickWait.Hospital.Infrastructure.Repositories;
using MediatR;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Options;

namespace FrwkBootCampQuickWait.Hospital.Application.Extensions
{
    public static class ServiceCollectionExtensions
    {
        public static IServiceCollection InjectHostedServices(this IServiceCollection services)
        {
            services
                .AddHostedService<HospitalConsumer>();

            return services;
        }

        public static IServiceCollection InjectSettings(this IServiceCollection services, IConfiguration configuration)
        {
            services
                .AddSettings<DatabaseSettings>(configuration)
                .AddSettings<RabbitSettings>(configuration);

            return services;
        }

        public static IServiceCollection InjectRepository(this IServiceCollection services, IConfiguration configuration)
        {
            services
                 .AddScoped(typeof(IBaseRepository<>), typeof(BaseRepository<>));

            return services;
        }

        public static IServiceCollection InjectDomain(this IServiceCollection services)
        {
            var domainAssembly = typeof(Entity).Assembly;

            services
                .AddMediatR(domainAssembly)
                .AddAutoMapper(domainAssembly);

            return services;
        }

        private static IServiceCollection AddSettings<T>(this IServiceCollection services, IConfiguration configuration) where T : Settings, new()
        {
            services.Configure<T>(configuration.GetSection(typeof(T).Name));

            var settings = services.BuildServiceProvider().GetRequiredService<IOptions<T>>().Value;

            return services.AddSingleton(settings);
        }
    }
}