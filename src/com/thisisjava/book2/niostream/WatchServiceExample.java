package com.thisisjava.book2.niostream;

/*
파일 이름을 바꾸면
해당 파일을 삭제 후 생성한것으로 나온다.
진짜 내부적으로 그렇게 돌아가고 있는걸까?
파일의 이름을 바꾸면 해당 파일을 삭제하고 해당 파일의 내용을 새로운 이름의 파일로 복사 붙여넣기를 한다?
에이 말도안돼
파일을 가리키는 참조 객체를 삭제후 생성이라고 하는것이면 말된다.
오홍... 모르겠군..
 */

import java.nio.file.*;
import java.util.List;

class WatchServiceThread extends Thread {
    @Override
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path directory = Paths.get("C:/Temp");
            directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                WatchKey watchKey = watchService.take(); // watchkey가 큐에 들어올때까지 블로킹
                List<WatchEvent<?>> list = watchKey.pollEvents();  // watchEvent 목록 얻기
                for (WatchEvent watchEvent : list) {
                    // 들어온 이벤트 종류 얻기
                    WatchEvent.Kind kind = watchEvent.kind();

                    // 감지된 path 얻기
                    Path path = (Path) watchEvent.context();
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        System.out.println("파일이 생성됨 -> " + path.getFileName());
                    } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("파일이 삭제됨 -> " + path.getFileName());
                    } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("파일이 수정됨 -> " + path.getFileName());
                    } else if (kind == StandardWatchEventKinds.OVERFLOW) {
                        System.out.println("oveflow");
                    }
                }
                boolean valid = watchKey.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (Exception e) {
        }
    }
}

public class WatchServiceExample {
    public static void main(String[] args) {
        WatchServiceThread wst = new WatchServiceThread();
        wst.start();
    }
}
