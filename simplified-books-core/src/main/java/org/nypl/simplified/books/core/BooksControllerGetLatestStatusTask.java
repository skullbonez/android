package org.nypl.simplified.books.core;

import com.io7m.jnull.NullCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * A task that simply loads the on-disk book status from the database and
 * broadcasts it.
 */

final class BooksControllerGetLatestStatusTask implements Runnable
{
  private static final Logger LOG;

  static {
    LOG = NullCheck.notNull(
      LoggerFactory.getLogger(BooksControllerSyncTask.class));
  }

  private final BookID               book_id;
  private final BookDatabaseType     books_database;
  private final BooksStatusCacheType book_status;

  BooksControllerGetLatestStatusTask(
    final BookDatabaseType in_book_database,
    final BooksStatusCacheType in_book_status,
    final BookID in_id)
  {
    this.books_database = NullCheck.notNull(in_book_database);
    this.book_id = NullCheck.notNull(in_id);
    this.book_status = NullCheck.notNull(in_book_status);
  }

  @Override public void run()
  {
    try {
      final BookDatabaseEntryReadableType e =
        this.books_database.databaseOpenEntryForReading(this.book_id);
      final BookDatabaseEntrySnapshot snap = e.entryGetSnapshot();
      this.book_status.booksStatusUpdate(
        BookStatus.fromSnapshot(
          this.book_id, snap));
    } catch (final IOException e) {
      BooksControllerGetLatestStatusTask.LOG.error(
        "[{}]: unable to fetch status: ", this.book_id.getShortID(), e);
    }
  }
}
