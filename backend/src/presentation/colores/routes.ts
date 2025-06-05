import { Router } from "express";
import { SQLiteRepository } from "../../data/sqlite/repositories/sqlite.repository";
import { SQLiteDatabase } from "../../data/sqlite/sqlite.database";
import { ColorModel } from "../../data/sqlite/models/color.entity";
import { ColorController } from "./controller";

export class ColorRoutes {
    constructor() { }

    static get routes(): Router {
        const router = Router();
        const database = SQLiteDatabase.getInstance({
            database: "dev.sqlite",
            entities: [ColorModel]
        })
        const repository = new SQLiteRepository<ColorModel>(ColorModel, database)
        const controller = new ColorController(repository)
        // Definir todos mis rutas principales
        router.get('/', controller.getAll);
        return router;
    }
}