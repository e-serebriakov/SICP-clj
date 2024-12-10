FROM clojure:temurin-17-tools-deps

# Install clojure-lsp
RUN apt-get update \
    && apt-get install -y curl \
    && mkdir -p /usr/local/bin \
    && curl -sL "https://github.com/clojure-lsp/clojure-lsp/releases/latest/download/clojure-lsp-native-linux-aarch64.zip" -o /tmp/clojure-lsp.zip \
    && apt-get install -y unzip \
    && unzip /tmp/clojure-lsp.zip -d /usr/local/bin \
    && chmod +x /usr/local/bin/clojure-lsp \
    && rm /tmp/clojure-lsp.zip