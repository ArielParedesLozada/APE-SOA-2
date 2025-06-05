import { Router } from "express";
import { SQLiteRepository } from "../../data/repositories/sqlite.repository";
import { ColorModel } from "../../data/models/color.entity";
import { ColorController } from "./controller";
import { SQLiteDatabase } from "../../data/sqlite/sqlite.database";

export class ColorRoutes {
    constructor() {
    }
    static routes(database: SQLiteDatabase): Router {
        const router = Router();
        const repository = new SQLiteRepository<ColorModel>(ColorModel, database)
        const controller = new ColorController(repository)
        // Definir todos mis rutas principales
        router.get('/', controller.getAll);
        return router;
    }
}