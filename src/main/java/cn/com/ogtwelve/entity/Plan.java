package cn.com.ogtwelve.entity;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 12:04
 */
public class Plan {
    private String title;
    private String id;

    public Plan() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Plan)) {
            return false;
        } else {
            Plan other = (Plan)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$title = this.getTitle();
                Object other$title = other.getTitle();
                if (this$title == null) {
                    if (other$title != null) {
                        return false;
                    }
                } else if (!this$title.equals(other$title)) {
                    return false;
                }

                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Plan;
    }

    public int hashCode() {
        int result = 1;
        Object $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public String toString() {
        return "Plan(title=" + this.getTitle() + ", id=" + this.getId() + ")";
    }
}
