using FrwkBootCampQuickWait.Hospital.Domain.Commands;
using FrwkBootCampQuickWait.Hospital.Domain.Configuration;
using FrwkBootCampQuickWait.Hospital.Domain.Services;
using MediatR;
using Microsoft.Extensions.Hosting;

namespace FrwkBootCampQuickWait.Hospital.Application.Consumers
{
    public sealed class HospitalConsumer : RabbitMqConsumer, IHostedService
    {
        protected override string ContextQueue => "hospital";

        public HospitalConsumer(RabbitSettings rabbitSettings, IMediator mediator) : base(rabbitSettings, mediator)
        {
        }

        public async Task StartAsync(CancellationToken cancellationToken)
        {
            await ConsumeAsync<AddNewHospitalCommand>("add", "add.new");
        }

        public Task StopAsync(CancellationToken cancellationToken)
        {
            throw new NotImplementedException();
        }
    }
}