import { AppRoutes } from "./presentation/routes";
import { Server } from "./presentation/server";
import { envs } from "./config/envs";
import { SQLiteDatabase } from "./data/sqlite/sqlite.database";
import { ColorModel } from "./data/models/color.entity";
import { VehiculoModel } from "./data/models/vehiculo.model";
import { MarcaModel } from "./data/models/marca.model";
import { ModeloModel } from "./data/models/modelo.model";

(() => {
    main();
})()


async function main() {
    const database = SQLiteDatabase.getInstance({
        database: "dev.sqlite",
        entities: [ColorModel, VehiculoModel, MarcaModel, ModeloModel]
    })

    await database.connect()

    new Server({
        port: envs.PORT,
        routes: AppRoutes.routes(database)
    })
        .start();
}