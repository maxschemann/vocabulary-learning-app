import {IndexCard} from "../model/IndexCard";
import axios from "axios";

export const postIndexCard: (indexCard: Omit<IndexCard, "id">) => Promise<IndexCard> = (indexCard) => {
    return axios.post("/api/indexcard", indexCard)
        .then(response => response.data)
}

export const getAllIndexCards = () => {
    return axios.get("/api/indexcard")
        .then(response => response.data)
}

