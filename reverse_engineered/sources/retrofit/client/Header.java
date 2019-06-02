package retrofit.client;

public final class Header {
    private final String name;
    private final String value;

    public Header(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Header header = (Header) obj;
        if (this.name == null ? header.name != null : !this.name.equals(header.name)) {
            return false;
        }
        if (this.value != null) {
            if (this.value.equals(header.value)) {
                return true;
            }
        } else if (header.value == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return (this.name != null ? this.name : "") + ": " + (this.value != null ? this.value : "");
    }
}
