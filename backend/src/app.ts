import { AppRoutes } from "./presentation/routes";
import { Server } from "./presentation/server";
import { envs } from "./config/envs";
import { SQLiteDatabase } from "./data/sqlite/sqlite.database";
import { ColorModel } from "./data/sqlite/models/color.entity";
import { VehiculoModel } from "./data/sqlite/models/vehiculo.model";
import { MarcaModel } from "./data/sqlite/models/marca.model";
import { Modelo } from "./domain/entities/modelo.entity";
import { ModeloModel } from "./data/sqlite/models/modelo.model";

(() => {
    main();
})()


async function main() {
    await SQLiteDatabase.getInstance({
        database: "dev.sqlite",
        entities: [ColorModel, VehiculoModel, MarcaModel, ModeloModel]
    }).connect()

    new Server({
        port: envs.PORT,
        routes: AppRoutes.routes
    })
        .start();
}