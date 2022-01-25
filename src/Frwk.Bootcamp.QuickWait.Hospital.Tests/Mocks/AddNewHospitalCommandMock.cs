using FrwkBootCampQuickWait.Hospital.Domain.Commands;
using FrwkBootCampQuickWait.Hospital.Domain.ValueObjects;

namespace Frwk.Bootcamp.QuickWait.Hospital.Tests.Mocks
{
    public static class AddNewHospitalCommandMock
    {
        public static AddNewHospitalCommand GetDefaultInstance() => new AddNewHospitalCommand
        {
            Name = "Teste Hospital",
            Address = new Address
            {
                City = "Teste Cidade",
                District = "Teste Bairro",
                Number = 1,
                Street = "Teste Rua"
            }
        };

        public static AddNewHospitalCommand GetEmptyInstance() => new AddNewHospitalCommand();

        public static AddNewHospitalCommand? GetNullInstance() => null;
    }
}
