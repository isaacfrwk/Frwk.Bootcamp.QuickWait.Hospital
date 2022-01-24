using FrwkBootCampQuickWait.Hospital.Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace FrwkBootCampQuickWait.Hospital.Domain.Extensions
{
    public static class QueryableExtensions
    {
        public static IQueryable<TEntity> AsNoTracking<TEntity>(this IQueryable<TEntity> items, bool asNoTracking) where TEntity : Entity
        {
            if (!asNoTracking)
                return items;

            return items.AsNoTracking();
        }
    }
}
