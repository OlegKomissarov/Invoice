package by.bsu.invoice.repository;

import by.bsu.invoice.entity.Service;

import java.util.List;

public interface ServiceRepository extends Repository<Service> {

    List<Service> getByUserId(final long userId);
}
