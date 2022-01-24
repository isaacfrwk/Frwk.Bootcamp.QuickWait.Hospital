using MediatR;

namespace FrwkBootCampQuickWait.Hospital.Domain.Commands
{
    public sealed class AddNewHospitalCommandHandler : IRequestHandler<AddNewHospitalCommand, bool>
    {
        public Task<bool> Handle(AddNewHospitalCommand request, CancellationToken cancellationToken)
        {
            var a = request;

            return Task.FromResult(true);
        }
    }
}
