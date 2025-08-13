// File: BuggyCpp350.cpp_chunk1
#include "zlib.h"

int compress2(Bytef* dest, uLongf* destLen, const Bytef* source, uLong sourceLen, int level) {
    z_stream strm{};
    strm.zalloc = nullptr;
    strm.zfree = nullptr;
    strm.opaque = nullptr;

    // Initialize the z_stream with deflate
    int ret = deflateInit_(&strm, level, ZLIB_VERSION, sizeof(z_stream));
    if (ret != Z_OK) {
        return ret;
    }

    // RAII guard to ensure deflateEnd is called
    struct DeflateEndGuard {
        z_stream* strm_;
        ~DeflateEndGuard() { deflateEnd(strm_); }
    } guard{&strm};

    // Check for input length overflow
    if (sourceLen > UINT_MAX) {
        return Z_BUF_ERROR;
    }
    strm.next_in = const_cast<Bytef*>(source); // Safe cast, input is not modified
    strm.avail_in = static_cast<uInt>(sourceLen);

    // Check for output length overflow
    if (*destLen > UINT_MAX) {
        return Z_BUF_ERROR;
    }
    strm.next_out = dest;
    strm.avail_out = static_cast<uInt>(*destLen);

    // Perform compression in one shot with Z_FINISH
    ret = deflate(&strm, Z_FINISH);

    // Handle the result of deflate
    if (ret == Z_STREAM_ERROR) {
        return ret;
    } else if (ret != Z_STREAM_END) {
        // If output buffer is full or other error, return appropriate code
        return (ret == Z_OK) ? Z_BUF_ERROR : ret;
    }

    // Set the actual compressed size
    *destLen = strm.total_out;
    return Z_OK;
}
#include <iostream>
#include <array>
#include <memory>

class Demo {
public:
    int x;
    Demo() : x(0) {}

    void show() const {
        std::cout << "Value: " << x << std::endl;
    }
};

int main() {
    Demo d;
    d.show(); // Use valid object on stack

    std::array<int, 3> arr = {10, 20, 30};
    for (const auto& num : arr) { // Range-based loop avoids bounds issues
        std::cout << num << std::endl;
    }

    auto p = std::make_unique<int>(42); // Smart pointer for automatic memory management

    int a = 10, b = 0;
    if (b != 0) {
        std::cout << "Division: " << a / b << std::endl;
    } else {
        std::cerr << "Error: Division by zero!" << std::endl;
    }

    for (int i = 0; i < 5; i++) { // Braces ensure both lines are in the loop
        std::cout << "i: " << i << std::endl;
        std::cout << "End of loop iteration" << std::endl;
    }

    Demo d2;
    for (int i = 0; i < 3; i++) {
        std::cout << "Object value: " << d2.x << std::endl;
        d2.x++;
    }

    std::cout << "Program ends." << std::endl;
    return 0;
}
// File: BuggyCpp350.cpp_chunk3
#include <zlib.h>
#include <cstdio>
#include <memory>
#include <cstring>
#include <fcntl.h>

struct gzFile_s {
    FILE* file;
    int mode;
    int err;
    int eof;
    int start;

    gzFile_s() : file(nullptr), mode(0), err(Z_OK), eof(0), start(0) {}

    ~gzFile_s() {
        if (file) {
            fclose(file);
            file = nullptr;
        }
    }
};

gzFile gz_open(const char* path, const char* mode, int fd) {
    if (!path || !mode) return nullptr;

    auto s = std::make_unique<gzFile_s>();
    if (!s) return nullptr;

    if (strchr(mode, 'r')) {
        s->mode = O_RDONLY;
    } else if (strchr(mode, 'w') || strchr(mode, 'a')) {
        s->mode = O_WRONLY;
    } else {
        return nullptr;
    }

    s->start = (s->mode == O_WRONLY) ? 0 : -1;

    if (fd < 0) {
        s->file = fopen(path, mode);
    } else {
        s->file = fdopen(fd, mode);
    }

    if (!s->file) {
        return nullptr;
    }

    return s.release();
}

size_t gz_read(gzFile file, void* buf, size_t len) {
    if (!file || !file->file || !buf || len == 0) return 0;

    size_t read = fread(buf, 1, len, file->file);
    if (read < len) {
        if (ferror(file->file)) {
            file->err = Z_ERRNO;
        }
        if (feof(file->file)) {
            file->eof = 1;
        }
    }
    return read;
}

size_t gz_write(gzFile file, const void* buf, size_t len) {
    if (!file || !file->file || !buf || len == 0) return 0;

    size_t written = fwrite(buf, 1, len, file->file);
    if (written < len) {
        file->err = Z_ERRNO;
    }
    return written;
}

void gz_close(gzFile file) {
    delete file;
}

int gz_eof(gzFile file) {
    return file ? file->eof : 0;
}

const char* gz_error(gzFile file, int* errnum) {
    if (file) {
        if (errnum) *errnum = file->err;
        return file->err ? zError(file->err) : "";
    }
    if (errnum) *errnum = Z_STREAM_ERROR;
    return zError(Z_STREAM_ERROR);
}
#include <iostream>

class Demo {
public:
    int x;
    Demo() : x(0) {}

    void show() {
        std::cout << "Value: " << x << std::endl;
    }
};

int main() {
    Demo d;
    d.show();  // Use stack-allocated object instead of NULL pointer

    const int arr[] = {10, 20, 30};
    for (int num : arr) {  // Range-based loop prevents out-of-bounds access
        std::cout << num << std::endl;
    }

    int value = 0;
    int* p = &value;  // Point to valid memory
    *p = 42;

    int a = 10, b = 0;
    if (b != 0) {  // Check for division by zero
        std::cout << "Division: " << a / b << std::endl;
    } else {
        std::cerr << "Error: Division by zero." << std::endl;
    }

    for (int i = 0; i < 5; ++i) {  // Braces ensure both lines are in the loop
        std::cout << "i: " << i << std::endl;
        std::cout << "End of loop" << std::endl;
    }

    Demo d2;
    for (int i = 0; i < 3; ++i) {
        std::cout << "Object value: " << d2.x << std::endl;
        d2.x++;
    }

    std::cout << "Program ends." << std::endl;
    return 0;
}
#include <iostream>
#include <vector>
#include <stdexcept>

class Demo {
private:
    int x; // Encapsulated data member
public:
    Demo() : x(0) {}

    void show() const {
        std::cout << "Value: " << x << '\n';
    }

    // Accessor and mutator for better encapsulation
    int getX() const { return x; }
    void incrementX() { ++x; }
};

int main() {
    Demo d; // Stack allocation instead of raw pointer
    d.show();

    std::vector<int> arr = {10, 20, 30}; // STL container
    for (size_t i = 0; i < arr.size(); ++i) { // Correct bounds
        std::cout << arr[i] << '\n';
    }

    int value = 0;
    int* p = &value; // Valid pointer initialization
    *p = 42;
    std::cout << "p value: " << *p << '\n';

    int a = 10, b = 0;
    if (b != 0) { // Check for division by zero
        std::cout << "Division: " << a / b << '\n';
    } else {
        std::cerr << "Error: Division by zero.\n";
    }

    for (int i = 0; i < 5; ++i) { // Braces for clear scope
        std::cout << "i: " << i << '\n';
    }
    std::cout << "End of loop\n";

    Demo d2;
    for (int i = 0; i < 3; ++i) {
        std::cout << "Object value: " << d2.getX() << '\n';
        d2.incrementX();
    }

    std::cout << "Program ends.\n";
    return 0;
}