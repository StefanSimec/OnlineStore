package ch.roche.ss.onlinestore.service.exception;

public class NotFoundException extends RuntimeException
{
    public NotFoundException(String name, Long id)
    {
        super(name+" not found for id: "+id);
    }
}
