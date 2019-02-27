package net.ozertsov.service;

import de.thjom.java.systemd.Manager;
import de.thjom.java.systemd.Service;
import de.thjom.java.systemd.Systemd;
import de.thjom.java.systemd.Unit;
import net.ozertsov.model.RegisteredService;
import org.freedesktop.dbus.Path;
import org.freedesktop.dbus.exceptions.DBusException;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.Map;

@org.springframework.stereotype.Service
@ConfigurationProperties
public class SystemdService {

    Manager manager;
    Map<String, RegisteredService> services;

    @PostConstruct
    public void print() {
        System.out.println(services);
    }


    public SystemdService() {
        try {
            manager = Systemd.get(Systemd.InstanceType.SYSTEM).getManager();
        } catch (DBusException e) {
            e.printStackTrace();
        }
    }

    public String start(String name) throws DBusException {
//        return null;
        manager.loadUnit(name);
        Service service = manager.getService(name);
        return service.start(Unit.Mode.REPLACE).toString();
    }

    public String stop(String name) throws DBusException {
        return null;
//        manager.loadUnit(name);
//        Service service = manager.getService(name);
//        return service.stop(Unit.Mode.REPLACE).toString();
    }

    public String status(String name) throws DBusException {
        name = "docker.service";
//        return null;
//        return services.get(0).toString();
        manager.loadUnit(name);
        Service service = manager.getService(name);
        return service.getActiveState();
    }

    public String getDescription(String alias) {
        return services.get(alias).getDescription();
    }

    public Object journal(String name) throws DBusException {
        name = "docker.service";
        manager.loadUnit(name);
        Service service = manager.getService(name);
        System.out.println(service.getStateDirectory());
        return service.getUser();
//        manager.getR

    }

    public int getPID(String alias) throws DBusException {
        alias = "docker.service";
        Path p = manager.loadUnit(alias);
        System.out.println(p);
        Service service = manager.getService(alias);
        System.out.println(service.getStatusText());
        return service.getMainPID();
    }

    public void setServices(Map<String, RegisteredService> services) {
        this.services = services;
    }
}
