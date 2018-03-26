package patterns.StrategyPattern;

/**
 * Created by Nano.son on 2018. 3. 25.
 */
public class BackGroundManager {
    private final Runnable task;
    private final DoSomething before_task;
    private final DoSomething after_task;

    private BackGroundManager(Builder builder) {
        this.task = builder.task;
        this.before_task = builder.before_task;
        this.after_task = builder.after_task;
    }

    public static class Builder {
        private final Runnable task;
        private DoSomething before_task;
        private DoSomething after_task;

        Builder (Runnable task) {
            if(task == null)
                throw new RuntimeException("Error, task is null !!");
            this.task = task;
            before_task = null;
            after_task = null;
        }

        public Builder beforeTask(DoSomething before_task) {
            this.before_task = before_task;
            return this;
        }

        public Builder afterTask(DoSomething after_task) {
            this.after_task = after_task;
            return this;
        }

        public BackGroundManager build() {
            return new BackGroundManager(this);
        }
    }

    public void run() {
        if(before_task != null)
            before_task.doSomething();
        task.run();
        if(after_task != null)
            after_task.doSomething();
    }
}
