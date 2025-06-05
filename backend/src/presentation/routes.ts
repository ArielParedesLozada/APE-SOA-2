import { Router } from "express";
import { ColorRoutes } from "./colores/routes";
import { SQLiteDatabase } from "../data/sqlite/sqlite.database";

export class AppRoutes {
    constructor() { }

    static routes(database: SQLiteDatabase): Router {
        const router = Router();
        // Definir todos mis rutas principales
        router.get('/', (req, res) => {
            res.send({
                message: "Hola Papus"
            })
        });

        router.use('/waza', ColorRoutes.routes(database))
        return router;
    }
}