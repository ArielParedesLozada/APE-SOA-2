export abstract class IRepository<T> {
    public abstract findAll(): T[];
    public abstract findById(id: number): T;
    public abstract create(created : T) : boolean;
    public abstract update(updated : T) : boolean;
    public abstract delete(deleted : T) : boolean;
}