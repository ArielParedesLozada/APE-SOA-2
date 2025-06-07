import { EntityTarget, ObjectLiteral, Repository } from "typeorm";
import { IDatabaseRepository } from "../repository.datasource";
import { SQLiteDatabase } from "../sqlite/sqlite.database";

export class SQLiteRepository<T extends ObjectLiteral> extends IDatabaseRepository<T> {
    private readonly datasource: Repository<T>
    constructor(
        entity: EntityTarget<T>,
        private readonly database: SQLiteDatabase,
    ) {
        super()
        if (!database.dataSource) {
            throw new Error("Base no inicializadas")
        }
        this.datasource = database.dataSource.getRepository(entity)
    }

    public findAll(): Promise<T[]> {
        return this.datasource.find()
    }

    public findById(id: number): Promise<T | null> {
        return this.datasource.findOneBy(id as any)
    }

    public async create(created: T): Promise<boolean> {
        await this.datasource.save(created);
        return true;
    }

    public async update(updated: T): Promise<boolean> {
        await this.datasource.save(updated);
        return true;
    }

    public async delete(deleted: T): Promise<boolean> {
        await this.datasource.remove(deleted);
        return true;
    }
}