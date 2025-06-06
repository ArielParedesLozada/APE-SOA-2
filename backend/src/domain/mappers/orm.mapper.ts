import Models from "../../data/models"
import Entities from "../entities"

const entities = Entities.implementation
const models = Models.implementation

const mapper = {
    entities: entities,
    models: models
}

export default mapper