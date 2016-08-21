package upwork.sample.core.models.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Bug implements Serializable {
    private static final long serialVersionUID = -5834997211145647816L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bug_id", unique = true, nullable = false)
    private Integer bugId;

    @Column(name = "bug_description")
    private String bugDescription;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", length = 15)
    private Date creationDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "bug_tag",
            joinColumns =
            @JoinColumn(name = "bug_id"),
            inverseJoinColumns =
            @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<Tag>();

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    public String getBugDescription() {
        return bugDescription;
    }

    public void setBugDescription(String bugDescription) {
        this.bugDescription = bugDescription;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((bugDescription == null) ? 0 : bugDescription.hashCode());
        result = prime * result + ((bugId == null) ? 0 : bugId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bug other = (Bug) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (bugDescription == null) {
            if (other.bugDescription != null)
                return false;
        } else if (!bugDescription.equals(other.bugDescription))
            return false;
        if (bugId == null) {
            if (other.bugId != null)
                return false;
        } else if (!bugId.equals(other.bugId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Bug [bugId=" + bugId + ", bugDescription=" + bugDescription + ", author=" + author + ", creationDate="
                + creationDate + "]";
    }
}
