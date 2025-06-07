export abstract class IDatabaseRepository<T> {
    public abstract findAll(relations?: string[]): Promise<T[]>;
    public abstract findById(id: number, relations?: string[]): Promise<T | null>;
    public abstract create(created : T) : Promise<boolean>;
    public abstract update(updated : T) : Promise<boolean>;
    public abstract delete(deleted : T) : Promise<boolean>;
}