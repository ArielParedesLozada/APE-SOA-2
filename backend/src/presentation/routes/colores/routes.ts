import { Router } from "express";
import { DatasourceFactory } from "../../../infraestructure/datasource/datasource.factory";
import { ColorController } from "./controller";
import { ColorModel } from "../../../data/models/color.model";
import { GlobalDatabase } from "../../../infraestructure/core/database.global";

export class ColorRoutes {
    constructor() {
    }
    static get routes(): Router {
        const router = Router();
        const database = GlobalDatabase.getInstance().database
        const repository = DatasourceFactory.generateRepository(database, ColorModel)
        if (!repository) {
            throw new Error("Repositorio no implementado")
        }
        const controller = new ColorController(repository)
        // Definir todos mis rutas principales
        router.get('/', controller.getAll);
        return router;
    }
}