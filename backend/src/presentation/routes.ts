import { Router } from "express";
import { SQLiteRepository } from "../data/sqlite/repositories/sqlite.repository";
import { SQLiteDatabase } from "../data/sqlite/sqlite.database";
import { ColorModel } from "../data/sqlite/models/color.entity";
import { IRepository } from "../data/interfaces/repository.datasource";
import { ColorRoutes } from "./colores/routes";

export class AppRoutes {
    constructor() { }

    static get routes(): Router {
        const router = Router();
        // Definir todos mis rutas principales
        router.get('/', (req, res) => {
            res.send({
                message: "Hola Papus"
            })
        });

        router.use('/waza', ColorRoutes.routes)
        return router;
    }
}