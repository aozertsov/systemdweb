package net.ozertsov.controller;

import net.ozertsov.service.SystemdService;
import org.freedesktop.dbus.exceptions.DBusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.loader.tools.LibraryScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1")
public class SystemdController {

    @Autowired
    SystemdService sysdService;

    @GetMapping("/{description}/status")
    public Object getStatus(@PathVariable String description) throws DBusException {
        return sysdService.status(description);
//        return description;
//        try {
//            return sysdService.status(description);
//        } catch (DBusException e) {
//            return null;
//        }
    }

    @GetMapping("/{alias}/description")
    public Object getDescription(@PathVariable String alias) {
        return sysdService.getDescription(alias);
    }

    @GetMapping("/{alias}/pid")
    public Object getPID(@PathVariable String alias) throws DBusException {
        return sysdService.getPID(alias);
    }

    @GetMapping("/{alias}/start")
    public Object getStart(@PathVariable String alias) throws DBusException {
        return sysdService.start(alias);
    }

    @GetMapping("/{alias}/journal")
    public Object getJournal(@PathVariable String alias) throws DBusException {
        return sysdService.journal(alias);
    }
}
