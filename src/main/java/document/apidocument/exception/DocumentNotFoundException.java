package document.apidocument.exception;

// 커스텀 예외 클래스
public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException(String message) {
        super(message);
    }
}