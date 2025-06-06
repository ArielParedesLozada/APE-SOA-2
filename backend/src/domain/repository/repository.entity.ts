export abstract class IRepo<T> {
    public abstract findAll(): Promise<T[]>;
    public abstract findById(id: number): Promise<T>;
    public abstract create(created : T) : Promise<boolean>;
    public abstract update(updated : T) : Promise<boolean>;
    public abstract delete(deleted : T) : Promise<boolean>;
}