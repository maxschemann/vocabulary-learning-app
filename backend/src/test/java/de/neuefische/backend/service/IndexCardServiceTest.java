package de.neuefische.backend.service;

import de.neuefische.backend.model.Difficulty;
import de.neuefische.backend.model.IndexCard;
import de.neuefische.backend.model.dto.IndexCardDto;
import de.neuefische.backend.repository.IndexCardRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexCardServiceTest {

    private final IndexCardRepository repo = mock(IndexCardRepository.class);
    private final IndexCardService service = new IndexCardService(repo);

    private final IndexCardDto testCardDto1= IndexCardDto.builder()
            .term1("test1")
            .term2("test2")
            .difficulty(Difficulty.EASY)
            .build();

    private final IndexCard testCard1 = IndexCard.builder()
            .id("123")
            .term1("test1")
            .term2("test2")
            .difficulty(Difficulty.EASY)
            .build();

    private final IndexCard testCard2 = IndexCard.builder()
            .id("456")
            .term1("test1")
            .term2("test2")
            .difficulty(Difficulty.EASY)
            .build();

    @Test
    void addNewIndexCard() {
        //given dto
        //when
        IndexCard mockCard_noId = IndexCard.builder()
                .term1("test1")
                .term2("test2")
                .difficulty(Difficulty.EASY)
                .build();

        when(repo.insert(mockCard_noId)).thenReturn(testCard1);
        IndexCard actual = service.addNewIndexCard(testCardDto1);
        //then
        IndexCard expected = IndexCard.builder()
                .id("123")
                .term1("test1")
                .term2("test2")
                .difficulty(Difficulty.EASY)
                .build();
        verify(repo).insert(mockCard_noId);
        assertEquals(expected, actual);
    }

    @Test
    void addNewIndexCard_WhenTermsMissing() {
        //given dtos with either term1 and/ or term2 missing
        IndexCardDto missingTerm1 = IndexCardDto.builder()
                .term2("test2")
                .difficulty(Difficulty.MEDIUM)
                .build();
        IndexCardDto missingTerm2 = IndexCardDto.builder()
                .term1("test1")
                .difficulty(Difficulty.MEDIUM)
                .build();
        IndexCardDto missingBothTerms = IndexCardDto.builder()
                .difficulty(Difficulty.MEDIUM)
                .build();
        IndexCardDto[] testCards = new IndexCardDto[]{missingTerm1, missingTerm2, missingBothTerms};
        //when adding, then throw IllegalArgumentException
        Arrays.stream(testCards).forEach( card -> {
            assertThrows(IllegalArgumentException.class, () -> service.addNewIndexCard(card));
        });
    }

    @Test
    void getAllIndexCards() {
        //given
        List<IndexCard> indexCards= List.of(testCard1, testCard2);
        //when
        when(repo.findAll()).thenReturn(indexCards);
        List <IndexCard> actual= service.getAllIndexCards();
        //then
        List<IndexCard> expected= List.of(testCard1, testCard2);
        verify(repo).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void updateIndexCard() {
        //given existing card, id, dto
        //when
        when(repo.save(testCard1)).thenReturn(testCard1);
        IndexCard actual = service.updateIndexCard(testCard1.getId(), testCardDto1);
        //then
        IndexCard expected = IndexCard.builder()
                .id(testCard1.getId())
                .term1("test1")
                .term2("test2")
                .difficulty(Difficulty.EASY)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    void deleteIndexCardById() {
        //given
        repo.deleteById("123");
        verify(repo).deleteById("123");
    }
}