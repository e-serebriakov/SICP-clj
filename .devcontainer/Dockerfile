FROM clojure:temurin-17-tools-deps

ARG USERNAME=developer
ARG USER_UID=1000
ARG USER_GID=$USER_UID

RUN groupadd --gid $USER_GID $USERNAME \
    && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME \
    && apt-get update \
    && apt-get install -y sudo\
    && echo $USERNAME ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/$USERNAME \
    && chmod 0440 /etc/sudoers.d/$USERNAME \
    && rm -rf /var/lib/apt/lists/*

# Install clojure-lsp
RUN apt-get update \
    && apt-get install -y curl sudo \
    && mkdir -p /usr/local/bin \
    && curl -sL "https://github.com/clojure-lsp/clojure-lsp/releases/latest/download/clojure-lsp-native-linux-aarch64.zip" -o /tmp/clojure-lsp.zip \
    && apt-get install -y unzip \
    && unzip /tmp/clojure-lsp.zip -d /usr/local/bin \
    && chmod +x /usr/local/bin/clojure-lsp \
    && rm /tmp/clojure-lsp.zip \
    && rm -rf /var/lib/apt/lists/*

USER $USERNAME