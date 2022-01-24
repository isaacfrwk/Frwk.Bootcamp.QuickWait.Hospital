using FrwkBootCampQuickWait.Hospital.Domain.Extensions;
using FrwkBootCampQuickWait.Hospital.Domain.ValueObjects;
using RabbitMQ.Client.Events;
using System.Text;

namespace FrwkBootCampQuickWait.Hospital.Domain.Services.Factories
{
    public static class RabbitMqFactory
    {
        public static Message GetMessage(BasicDeliverEventArgs basicDeliverEvent)
        {
            var messageBody = basicDeliverEvent.Body.ToArray();
            var messageConverted = Encoding.UTF8.GetString(messageBody);
            return messageConverted.Parse();
        }
    }
}