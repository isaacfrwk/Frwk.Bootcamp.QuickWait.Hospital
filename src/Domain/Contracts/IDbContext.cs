using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace FrwkBootCampQuickWait.Hospital.Domain.Contracts
{
    public interface IDbContext : IDisposable
    {
        DbSet<TEntity> Set<TEntity>() where TEntity : class;
        EntityEntry<TEntity> Entry<TEntity>(TEntity entity) where TEntity : class;
        Task<int> SaveChangesAsync(CancellationToken cancellationToken = default);
        int SaveChanges();
    }
}
