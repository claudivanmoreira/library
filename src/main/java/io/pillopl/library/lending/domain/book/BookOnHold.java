package io.pillopl.library.lending.domain.book;

import io.pillopl.library.lending.domain.library.LibraryBranchId;
import io.pillopl.library.lending.domain.patron.PatronBooksEvent.BookCollected;
import io.pillopl.library.lending.domain.patron.PatronBooksEvent.BookReturned;
import io.pillopl.library.lending.domain.patron.PatronId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class BookOnHold {

    private final BookInformation bookInformation;

    private final LibraryBranchId holdPlacedAt;

    private final PatronId byPatron;

    private final Instant holdTill;

    AvailableBook handle(BookReturned bookReturned) {
        return new AvailableBook(
                new BookInformation(new BookId(bookReturned.getBookId()), bookReturned.getBookType()),
                new LibraryBranchId(bookReturned.getLibraryBranchId()));
    }

    CollectedBook handle(BookCollected bookCollected) {
        return new CollectedBook(
                new BookInformation(new BookId(bookCollected.getBookId()), bookCollected.getBookType()),
                new LibraryBranchId(bookCollected.getLibraryBranchId()),
                new PatronId(bookCollected.getPatronId()));
    }

    public BookId getBookId() {
        return bookInformation.getBookId();
    }

}

