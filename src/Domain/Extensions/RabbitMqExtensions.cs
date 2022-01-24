using FrwkBootCampQuickWait.Hospital.Domain.ValueObjects;
using MediatR;
using System.Text.Json;

namespace FrwkBootCampQuickWait.Hospital.Domain.Extensions
{
    public static class RabbitMqExtensions
    {
        public static Message Parse(this string message)
        {
            if (string.IsNullOrEmpty(message))
                return new Message();

            return JsonSerializer.Deserialize<Message>(message, GetOptions());
        }

        public static IRequest<bool> Parse<TCommand>(this object payload) where TCommand : IRequest<bool>
        {
            return JsonSerializer.Deserialize<TCommand>(payload.ToJson(), GetOptions());
        }

        public static string ToJson(this object payload) => JsonSerializer.Serialize(payload);

        private static JsonSerializerOptions GetOptions()
        {
            var options = new JsonSerializerOptions
            {
                PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
            };

            return options;
        }
    }
}