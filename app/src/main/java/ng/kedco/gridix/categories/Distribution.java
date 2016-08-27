package ng.kedco.gridix.categories;

/**
 * Created by shaibujnr on 8/17/16.
 */

public class Distribution {
    private String name;
    private InjectionSubstation  injectionSource;

    public Distribution(String name, InjectionSubstation injectionSource) {
        this.name = name;
        this.injectionSource = injectionSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InjectionSubstation getInjectionSource() {
        return injectionSource;
    }

    public void setInjectionSource(InjectionSubstation injectionSource) {
        this.injectionSource = injectionSource;
    }
}
