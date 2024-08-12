# Custom HTTP Server

This project is a simple HTTP server implemented in Java. It handles various types of HTTP requests, supports file operations, and can serve static files. The server also includes functionality for echoing request paths, retrieving user-agent headers, and supporting gzip compression.

## Features

- **Supports HTTP Methods**: Handles GET and POST requests.
- **File Operations**: Serves files from a specified directory and supports file uploads.
- **Echo Requests**: Returns the request path as a response.
- **User-Agent Header**: Returns the `User-Agent` header from the request.
- **Gzip Compression**: Supports gzip compression for responses.
- **Error Handling**: Returns appropriate HTTP status codes for various conditions.

## Architecture

The server uses the `RequestHandler` class to process incoming HTTP requests. It performs the following tasks:

1. **Request Parsing**: Parses incoming HTTP requests to determine the method, path, and headers.
2. **Request Handling**: Routes requests based on the path and method.
3. **Response Generation**: Generates appropriate HTTP responses, including file content and custom messages.
4. **Error Handling**: Returns status codes for errors such as file not found or method not allowed.
