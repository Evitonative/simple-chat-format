# Simple Chat Format
This minecraft plugin for 1.19.1+ allows you to change the layout of chat messages. 
It uses (and requires) [Vault](https://www.spigotmc.org/resources/vault.34315/) to get the prefix and suffix of the player.

Players who have the `scm.inMessage` permission can use [formatting codes](#formatting) in their messages.

This plugin also shows the message as preview. When chat chat preview is enabled on the server, chat messages won't show up as edited by the server.

## Config
```yaml
#Set the message layout. Valid placeholders:
# {prefix}      -  The prefix set by your permission plugin
# {suffix}      -  The suffix set by your permission plugin
# {displayname} -  The players displayname
# {username}    -  The players displayname
# {message}     -  The message send by the player
layout: "&r{prefix}{displayname}{suffix}&r: {messsage}"
```
## Permission

| Permission    | Default | Description                                 |
|---------------|---------|---------------------------------------------|
| scm.inMessage | true    | Allows using of formatting codes in message |


## Formatting

| Format  | Result        |
|---------|---------------|
| &0      | Black         |
| &1      | Dark Blue     |
| &2      | Dark Green    |
| &3      | Dark Aqua     |
| &4      | Dark Red      |
| &5      | Dark Purple   |
| &6      | Gold          |
| &7      | Gray          |
| &8      | Dark Gray     |
 | &9      | Blue          |
| &a      | Green         |
| &b      | Aqua          |
| &c      | Red           |
| &d      | Light Purple  |
| &e      | Yellow        |
| &f      | White         |
| &#[HEX] | Hex Colour    |
| &k      | Bold          |
| &l      | Bold          |
| &m      | Strikethrough |
| &o      | Italic        |
| &r      | Reset         |

