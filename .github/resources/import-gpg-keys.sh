#!/bin/bash
set -euo pipefail

echo "🔐 Importing GPG key..."

# Prepare GnuPG environment
mkdir -p ~/.gnupg
chmod 700 ~/.gnupg

echo "allow-loopback-pinentry" >> ~/.gnupg/gpg-agent.conf
echo "pinentry-mode loopback" >> ~/.gnupg/gpg.conf
echo "use-agent" >> ~/.gnupg/gpg.conf

# Import the GPG private key
echo "${GPG_PRIVATE_KEY}" | gpg --batch --import

# Extract key ID
KEY_ID=$(gpg --list-secret-keys --with-colons | grep '^sec' | cut -d':' -f5)

# Set default key and trust level
echo "default-key $KEY_ID" >> ~/.gnupg/gpg.conf
echo -e "$KEY_ID:6:\n" | gpg --import-ownertrust

# Reload gpg-agent to pick up new config
gpgconf --reload gpg-agent

# Configure gpg-agent to use passphrase
export GPG_TTY=$(tty)
echo "🔑 GPG key setup complete for key: $KEY_ID"
