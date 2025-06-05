import { ColorModel } from "../../data/sqlite/models/color.entity";
import { SQLiteRepository } from "../../data/sqlite/repositories/sqlite.repository";
import { Color } from "../entities/color.entity";
import { IRepository } from "../repository/repository.entity";

export class ColorRepository extends IRepository<Color> {
    constructor(
        private readonly repository: SQLiteRepository<ColorModel>
    ) {
        super();
    }

    public findAll(): Color[] {
        throw new Error("Method not implemented.");
    }

    public findById(id: number): Color {
        throw new Error("Method not implemented.");
    }

    public create(created: Color): boolean {
        throw new Error("Method not implemented.");
    }

    public update(updated: Color): boolean {
        throw new Error("Method not implemented.");
    }

    public delete(deleted: Color): boolean {
        throw new Error("Method not implemented.");
    }

}