import IndexCardComponent from "./IndexCardComponent";
import React from "react";
import {IndexCard} from "../model/IndexCard";
import '../styles/IndexCardOverview.css';

type IndexCardOverviewProps = {
    indexCards: IndexCard[],
    removeIndexCard: (id: string) => void
    addNewIndexCard: (indexCard: Omit<IndexCard, "id">) => void,
    updateIndexCard: (id: string, indexCard: Omit<IndexCard, "id">) => void
}

export default function IndexCardOverview({
                                              indexCards,
                                              removeIndexCard,
                                              addNewIndexCard,
                                              updateIndexCard
                                          }: IndexCardOverviewProps) {

    return (
        <div id={"overview"}>
            {indexCards.map(card => <IndexCardComponent indexCard={card}
                                                        removeIndexCard={removeIndexCard}
                                                        addNewIndexCard={addNewIndexCard}
                                                        updateIndexCard={updateIndexCard}
                                                        key={card.id}
            />)}
        </div>
    )
}