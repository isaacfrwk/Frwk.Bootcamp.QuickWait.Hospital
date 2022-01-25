﻿using FrwkBootCampQuickWait.Hospital.Domain.ValueObjects;
using MediatR;

namespace FrwkBootCampQuickWait.Hospital.Domain.Commands
{
    public sealed class AddNewHospitalCommand : IRequest<bool>
    {
        public string Name { get; set; }
        public Address Address { get; set; }
    }
}
