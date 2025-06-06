import { ColorModel } from "../../data/models/color.model";
import { IRepository } from "../../data/repository.datasource";
import { Color } from "../entities/color.entity";
import { IRepo } from "./repository.entity";

export class RepositoryImpl<T> extends IRepo<T> {
    constructor(
        private readonly repo: IRepository<any>
    ) {
        super();

    }
    public findAll(): Promise<T[]> {
        throw new Error("Method not implemented.");
    }
    public findById(id: number): Promise<T> {
        throw new Error("Method not implemented.");
    }
    public create(created: T): Promise<boolean> {
        throw new Error("Method not implemented.");
    }
    public update(updated: T): Promise<boolean> {
        throw new Error("Method not implemented.");
    }
    public delete(deleted: T): Promise<boolean> {
        throw new Error("Method not implemented.");
    }
}