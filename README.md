# Don't make me think about logging

            String formattedLine = String.format("%s|%s|%s|%s|%s|%s:%d%s|%s",
                    OffsetDateTime.now(),
                    level,
                    Thread.currentThread().getName(),
                    caller.getClassName(),
                    caller.getMethodName(),
                    caller.getFileName(),
                    caller.getLineNumber(),
                    tagsAsString(),
                    something);
