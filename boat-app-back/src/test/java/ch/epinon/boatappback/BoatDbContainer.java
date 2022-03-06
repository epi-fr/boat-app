package ch.epinon.boatappback;

import org.testcontainers.containers.MySQLContainer;

public class BoatDbContainer extends MySQLContainer<BoatDbContainer> {
    private static final String IMAGE = "mysql";
    private static BoatDbContainer container;

    public BoatDbContainer() {
        super(IMAGE);
    }

    public static BoatDbContainer getInstance() {
        if (container == null) {
            container = new BoatDbContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

}
