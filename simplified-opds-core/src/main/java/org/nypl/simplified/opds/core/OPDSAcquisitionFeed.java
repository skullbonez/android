package org.nypl.simplified.opds.core;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.io7m.jfunctional.Option;
import com.io7m.jfunctional.OptionType;
import com.io7m.jnull.NullCheck;
import com.io7m.jnull.Nullable;

/**
 * The type of OPDS acquisition feeds.
 */

@SuppressWarnings("synthetic-access") public final class OPDSAcquisitionFeed implements
  OPDSFeedType
{
  private static final class Builder implements
    OPDSAcquisitionFeedBuilderType
  {
    private final List<OPDSAcquisitionFeedEntry> entries;
    private final String                         id;
    private OptionType<URI>                      next;
    private final String                         title;
    private final Calendar                       updated;
    private final URI                            uri;

    private Builder(
      final URI in_uri,
      final String in_title,
      final String in_id,
      final Calendar in_updated)
    {
      this.uri = NullCheck.notNull(in_uri);
      this.title = NullCheck.notNull(in_title);
      this.id = NullCheck.notNull(in_id);
      this.updated = NullCheck.notNull(in_updated);
      this.entries = new ArrayList<OPDSAcquisitionFeedEntry>();
      this.next = Option.none();
    }

    @Override public void addEntry(
      final OPDSAcquisitionFeedEntry e)
    {
      this.entries.add(NullCheck.notNull(e));
    }

    @Override public OPDSAcquisitionFeed build()
    {
      return new OPDSAcquisitionFeed(
        this.uri,
        this.entries,
        this.id,
        this.updated,
        this.title,
        this.next);
    }

    @Override public void setNextOption(
      final OptionType<URI> in_next)
    {
      this.next = NullCheck.notNull(in_next);
    }
  }

  private static final long serialVersionUID = -7962463871020194252L;

  public static OPDSAcquisitionFeedBuilderType newBuilder(
    final URI in_uri,
    final String in_id,
    final Calendar in_updated,
    final String in_title)
  {
    return new Builder(in_uri, in_title, in_id, in_updated);
  }

  private final List<OPDSAcquisitionFeedEntry> entries;
  private final String                         id;
  private final OptionType<URI>                next;
  private final String                         title;
  private final Calendar                       updated;
  private final URI                            uri;

  private OPDSAcquisitionFeed(
    final URI in_uri,
    final List<OPDSAcquisitionFeedEntry> in_entries,
    final String in_id,
    final Calendar in_updated,
    final String in_title,
    final OptionType<URI> in_next)
  {
    this.uri = NullCheck.notNull(in_uri);
    this.entries =
      NullCheck.notNull(Collections.unmodifiableList(in_entries));
    this.id = NullCheck.notNull(in_id);
    this.updated = NullCheck.notNull(in_updated);
    this.title = NullCheck.notNull(in_title);
    this.next = NullCheck.notNull(in_next);
  }

  @Override public boolean equals(
    final @Nullable Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final OPDSAcquisitionFeed other = (OPDSAcquisitionFeed) obj;
    return this.uri.equals(other.uri)
      && this.entries.equals(other.entries)
      && this.id.equals(other.id)
      && this.title.equals(other.title)
      && this.updated.equals(other.updated)
      && this.next.equals(other.next);
  }

  public List<OPDSAcquisitionFeedEntry> getFeedEntries()
  {
    return this.entries;
  }

  @Override public String getFeedID()
  {
    return this.id;
  }

  @Override public String getFeedTitle()
  {
    return this.title;
  }

  @Override public Calendar getFeedUpdated()
  {
    return this.updated;
  }

  @Override public URI getFeedURI()
  {
    return this.uri;
  }

  public OptionType<URI> getNext()
  {
    return this.next;
  }

  @Override public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + this.uri.hashCode();
    result = (prime * result) + this.entries.hashCode();
    result = (prime * result) + this.id.hashCode();
    result = (prime * result) + this.title.hashCode();
    result = (prime * result) + this.updated.hashCode();
    result = (prime * result) + this.next.hashCode();
    return result;
  }

  @Override public <A, E extends Exception> A matchFeedType(
    final OPDSFeedMatcherType<A, E> m)
    throws E
  {
    return m.acquisition(this);
  }
}
